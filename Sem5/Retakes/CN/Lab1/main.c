#include <arpa/inet.h>
#include <pthread.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <signal.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

int client_sock;

char *ip_list[30];
int ip_list_len;

typedef struct {
	int sock;
	char *ip;
} ClientData;

void time_out(int signal) {
  int32_t res = htonl(-1);

  printf("Time out.\n");

  send(client_sock, &res, sizeof(int32_t), 0);

  if (close(client_sock) != 0) {
    perror("Could not close socket.");
    exit(-6);
  }
}

void treat_client_conn(ClientData *client_data) {
  int32_t res;

  int no_bytes = recv(client_data->sock, &res, sizeof(int32_t), 0);

  res = ntohl(res);
  if (no_bytes != sizeof(int32_t)) {
    perror("Couldn't read.");
    exit(-5);
  }

  printf("%s sent the number: %d\n", client_data->ip, res);
  close(client_data->sock);

  exit(0);
}

int main() {
  int sock = socket(AF_INET, SOCK_STREAM, 0);
  if (sock == -1) {
    perror("Could not open socket.");
    return -3;
  }

  if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &(int){1}, sizeof(int)) < 0) {
    perror("failed.");
    return -7;
  }

  struct sockaddr_in server;
  memset(&server, 0, sizeof(server));

  server.sin_port = htons(9092);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;

  int bind_err = bind(sock, (struct sockaddr *)&server, sizeof(server));
  if (bind_err == -1) {
    perror("Could not bind socket.");
    return -2;
  }


  if (listen(sock, 7) == -1) {
    perror("Could not listen.");
    return -1;
  }

  pthread_t threads[30];

  struct sockaddr_in client_addr;
  socklen_t client_addr_len;

  printf("Listening...\n");

  while (1) {
    printf("Listening for new connection...\n");
    client_sock =
        accept(sock, (struct sockaddr *)&client_addr, &client_addr_len);
    printf("Client accepted\n");
	

    int empty_thread_index = 30;
    for (int i = 0; i < 30; i++) {
	if (threads[i] == 0) {
		empty_thread_index = i;
		break;
	}
    }
    if (empty_thread_index == 30) {
	perror("all threads busy");
	continue;
    }

    char *client_ip = inet_ntoa(client_addr.sin_addr);
	ClientData client_data = {
		.sock = client_sock,
		.ip = client_ip
	};

 	 int err_code = pthread_create(&threads[empty_thread_index], NULL, (void*) treat_client_conn, (void *)&client_data);
	 if (err_code != 0) {
		perror("couldn't spawn thread");
		exit(err_code);
	 }
    
    signal(SIGALRM, time_out);
    alarm(10);


    if (client_sock == -1) {
      perror("Could not connect to client.");
      return -4;
    }
  }
  return 0;
}
