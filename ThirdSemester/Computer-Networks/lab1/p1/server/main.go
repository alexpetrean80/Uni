package main

import (
	"fmt"
	"log"
	"net"
)

func main() {
	listenerAddr, err := net.ResolveUDPAddr("udp4", "0.0.0.0:4444")
	if err != nil {
		log.Fatal(err.Error())
	}
	serverConn, err := net.ListenUDP("udp4", listenerAddr)
	if err != nil {
		log.Fatal(err.Error())
	}
	defer serverConn.Close()

	buff := make([]byte, 1024)
	for {
		n, addr, err := serverConn.ReadFromUDP(buff)
		if err != nil {
			log.Fatal(err.Error())
		}
		fmt.Println("Received ", string(buff[:n]), " from ", addr)
		response := []byte(string(buff[:n]) + string(buff[:n]))
		serverConn.WriteMsgUDP(response, nil, addr)
	}
}
