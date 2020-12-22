# Easy Travel
Projeto para a disciplina de Algoritmos e Programação 2, ministrada pelos professores Diego Américo Guedes e Ricardo Augusto Pereira Franco pela Universidade Federal de Goiás (UFG), por Veronica Scislewski (@cybervee) e Jean Lucas (@uaijean). 

Desenvolvimento: de 03/11 a 08/12/2020.

A Easy Travel é um sistema de passagens aéreas que oferece o serviço de compra de passagens aéreas. O cliente pode escolher vôos e se deseja assentos de primeira ou segunda classe
mediante pagamento de taxa correspondente. 

O preço das passagens é definido pela soma das taxas de serviço e de assento. Crianças abaixo de 8 anos possuem direito ao desconto de 50% sob o valor final da passagem, e não podem viajar desacompanhadas.

As formas de pagamento aceitas são cartões de crédito e débito, que passam por duas validações: a de número (utilizando o Algoritmo de Luhn) e a de saldo/limite. Ao realizar a compra, cada passageiro é designado um número de voucher com 10 caracteres gerados aleatoriamente, entre letras maíusculas, minúsculas e algarismos.

# O Sistema
O sistema é composto por 8 classes que utilizam os conceitos de abstração, herança e polimorfismo. São elas, em ordem alfabética:

    • Cartao;
    • CartaoCredito, extende Cartao;
    • CartaoDebito, extende Cartao;
    • Main;
    • Passageiro;
    • PassageiroCrianca, extende Passageiro;
    • Passagem;
    • Voo.

Também fazem parte 6 exceções, que informam o usuário sobre uma possível falha:

		• CartaoNumeroInvalido;
		• CartaoRecusado;
		• CPFInvalido;
		• DadoNaoPreenchido;
		• IdadeCriancaInvalida;
		• QuantidadePassageirosInvalida.

Na classe Cartao, a abstração foi escolhida ao invés de interface porque a mesma contém métodos abstratos que são implementados nas classes herdeiras e o método de validação do número do cartão, que é o mesmo para ambos os tipos. 

A classe PassageiroCrianca extende a classe Passageiro por causa da necessidade de validação de idade para o desconto. Caso a criança possua mais de 8 anos, o sistema informa que é preciso cadastrá-la como um passageiro normal, pois não terá direito à redução do valor da passagem. 

A classe Passagem é obrigatoriamente composta por um passageiro, uma vez que não existe passageiro sem passagem e vice versa. Também possui o atributo String voucher, que é gerado automaticamente depois que a compra é aprovada. 

A classe Voo guarda todos as informações necessárias sobre o vôo desejado, como local de origem, chegada, quantidade de assentos e taxas. A ArrayList listaPassageiros contém todos os passageiros confirmados; se o cartão informado não for validado, o cadastro do passageiro é apagado da lista e a quantidade de assentos anterior é restaurada. 
