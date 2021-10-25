#include <arpa/inet.h>
#include <netdb.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>

#define EVER                                                                   \
  ;                                                                            \
  ;

void listen_udp(int sock) {
  int nr_bytes;
  char buff[1024];
  socklen_t client_len = sizeof(struct sockaddr_in);
  struct sockaddr_in client;

  for (EVER) {
    printf("Waiting for client conn...\n");
    nr_bytes = recvfrom(sock, buff, sizeof(buff), 0, (struct sockaddr *)&client,
                        &client_len);
    if (nr_bytes == 0) {
      printf("client shutdown");
      continue;
    }
    if (nr_bytes == -1) {
      perror("could not read");
      exit(-3);
    }

    char *client_ip = inet_ntoa(client.sin_addr);

    if (nr_bytes > sizeof(buff) - 2) {
	perror("crap overflow");
	exit(-4);
    }

    buff[nr_bytes] = '\0';
    printf("%s sent the text: %s\n", client_ip, buff);
  }
}

int main() {
  int sock = socket(AF_INET, SOCK_DGRAM, 0);
  if (sock == -1) {
    perror("could not open socket");
    exit(-1);
  }

  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;
  server.sin_port = htons(9092);

  if (bind(sock, (struct sockaddr *)&server, sizeof(server)) == -1) {
    perror("could not bind socket");
    exit(-2);
  }

  listen_udp(sock);

  return 0;
}
