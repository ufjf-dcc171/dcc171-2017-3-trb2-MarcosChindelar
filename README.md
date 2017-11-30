Trabalho 02
Nome: Marcos Roberto Chindelar de Olvieira Leite
Matricula: 201165248C 
Curso: Ciência da Computação

O protocolo de persistência desenvolvido consiste na utilização de dois arquivos de texto para armazenar as informações da aplicação: 
“entrada.txt” e “saida.txt”. O arquivo “entrada.txt” armazena somente os dados da aplicação, ou seja, os valores brutos(numéricos) 
presentes, com o objetivo de facilitar a leitura feita pela aplicação, enquanto o arquivo “sainda.txt” armazena não só os dados da 
aplicação como o que eles representam de forma legível para as pessoas que utilizem a aplicação. 

A edição dos arquivos se dá a cada alteração feita nos dados da aplicação: A cada função de criação (abertura de mesa, criação de pedidos 
e adição de itens aos pedidos por exemplo), a cada função de alteração (fechamento de pedidos e alteração de itens do pedido por exemplo) e
a cada função de exclusão (exclusão de itens de um pedido ou do próprio pedido). Ambos os arquivos são sempre alterados de maneira 
simultânea para que fiquem sempre sincronizados, ou seja, com os dados sempre idênticos, diferenciando-se apenas na forma de 
exibição dos mesmos. As funções le() e escreve() são respectivamente as funções de leitura e escrita dos arquvios de texto. Ambas são chamadas a cada alteração nos dados, conforme mencionado anteriormente e estão presentes na classe JanelaControle, que é a classe que gerencia a janela principal da apliacação. 

Ao iniciar a aplicação busca pelo arquivo de entrada, caso o arquivo não exista a aplicação informa ao usuário e 
começa vazia (sem dados de entrada), caso exista o arquivo de entrada, a aplicação o lê e carrega esses dados para a aplicação e 
para o arquivo de saída. 

Como melhoria para a aplicação podem ser feitas algumas mudanças como:

>Alterar os métodos de leitura e escrita para ficarem mais claros.
>Substituir o uso da classe Date para a Calendar.
>Adicionar o tratamento de algumas exceções para que a aplicação tenha um maior cuidado com os valores lidos nos arquivos.
