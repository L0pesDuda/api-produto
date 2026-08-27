package br.com.senai.produtosapi.controller;

public record LoginRequest(String username, String password) {
}

//Este código define uma classe de registro chamada LoginRequest no pacote br.com.senai.produtosapi.controller.
//  A classe possui dois campos: username e password, ambos do tipo String. 
// A utilização de um record em Java permite criar uma classe imutável com menos código, fornecendo automaticamente métodos como equals(), hashCode() e toString().