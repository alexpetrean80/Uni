#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>

int main() {
  int sock = socket(AF_INET, SOCK_STREAM, 0);
  if (sock == -1) {
    perror("Could not open socket.");
    return -3;
  }

  struct sockaddr_in server;
  memset(&server, 0, sizeof(server));

  server.sin_port = htons(9090);
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

  struct sockaddr_in client_addr;
  socklen_t client_addr_len;
  int client_sock;
  while (1) {
    printf("Listening...\n");

    client_sock =
        accept(sock, (struct sockaddr *)&client_addr, &client_addr_len);

    if (client_sock == -1) {
      perror("Could not connect to client.");
      return -4;
    }
    printf("%s\n", inet_ntoa(client_addr.sin_addr));
  }
  return 0;
}
