// Se transmite o litera de la client la server, serverul trimite inapoi litera dublata
package main

import (
	"fmt"
	"log"
	"net"
	"os"
)

func main() {
	argv := os.Args[1:]
	argc := len(argv)
	if argc != 1 {
		log.Fatal("the program must receive exactly one arg")
	}
	char := argv[0]
	if len(char) != 1 {
		log.Fatal("the arg must be a single character")
	}

	addr, err := net.ResolveUDPAddr("udp4", "127.0.0.1:4444")
	if err != nil {
		log.Fatal(err.Error())
	}
	conn, err := net.DialUDP("udp4", nil, addr)
	if err != nil {
		log.Fatal(err.Error())
	}
	defer conn.Close()
	conn.WriteMsgUDP([]byte(char), nil, addr)
	response := make([]byte, 2)
	conn.ReadFromUDP(response)
	fmt.Println(response)

}
