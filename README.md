# Analise de Dados Ilegra
Programa desenvolvido para o teste do processo seletivo da ilegra

O objetivo do teste é criar um programa que leia continuamente arquivos inseridos em um diretório pré definido, realizar a leitura do mesmo e criar um arquivo de saída com informações relevantes sobre o arquivo de entrada.

Exemplo do arquivo de entrada:
001ç1234567891234çPedroç50000 
001ç3245678865434çPauloç40000.99 
002ç2345675434544345çJose da SilvaçRural 
002ç2345675433444345çEduardo PereiraçRural 
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro 
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo 

Exemplo do arquivo de saída:
Quantidade de clientes: 2
Quantidade de vendedores: 2
ID da venda mais cara: 10
Pior vendedor: Paulo

Fluxo pensado:
O programa continuamente respeitando o intervalo definido no arquivo config.properties pela variável timer.intervalo;

1°Passo:
Buscar todos os arquivos presentes no diretório de entrada e criar um ArrayList com eles;
2°Passo:
Percorrer a lista e realizar a lógica para leitura e extração de informações do arquivo;
3°Passo:
Montar o arquivo de saída;
4°Passo:
Mover os arquivos lidos para uma pasta de histórico para que não haja conflito ou perca de performance no 1° Passo ao tentar identificar os arquivos que já foram lidos.

Escalabilidade:
Visando a boa perfomance mesmo com arquivos extensos, busquei alocar o mínimo de memória possível. A maior parte dos objetos criados foram lidos e descartados logo em seguida. Podemos notar que a única lista de objetos criada durante a leitura de um arquivo é a de Vendedores, pois é necessário comparar a quantia vendida por cada um para determinar o pior. A utilização da API Streams do Java 8, também nos permite possuir uma ótima performance se tratando de grande quantidade de dados.

Adicionais:
Utilizei a biblioteca log4j para realizar o monitoramento do Job criado, que pode ser visto no diretório ${user.home}\data\log.
Criei alguns casos de teste utilizando a biblioteca Junit, procurando minimizar os bugs do software desenvolvido.
