# Simulador de Algoritmos de Substituição de Páginas

## Resumo
Este projeto tem como objetivo **simular e comparar o desempenho** de diferentes **algoritmos de substituição de páginas** utilizados em sistemas de gerenciamento de memória virtual.  
Os algoritmos implementados são: **FIFO**, **LRU**, **Relógio (Clock)**, **Ótimo**, **NFU** e **Envelhecimento (Aging)**.  

O simulador permite observar e comparar o número de faltas de páginas em cada método, auxiliando no entendimento do comportamento dos algoritmos sob diferentes condições de acesso à memória.

---

## Objetivos
- Implementar quatro algoritmos de substituição de páginas (mínimo exigido);
- Comparar o número de faltas de página geradas por cada algoritmo;
- Exibir os resultados de forma clara e comparativa;
- **(Extra)**: apresentar os resultados graficamente através de uma interface gráfica (Swing ou PrimeFaces).

---

## ⚙️ Metodologia
O projeto foi desenvolvido em **Java**, utilizando programação orientada a objetos para modularizar os algoritmos.  
A entrada consiste em uma **cadeia de números inteiros**, representando as páginas referenciadas.  
Cada algoritmo é executado com base nessa sequência, e o simulador calcula o total de **faltas de página** ocorridas.

### Estrutura do Código
```
src/
 ┣ utils/
 ┃ ┣ FIFO.java
 ┃ ┣ LRU.java
 ┃ ┣ Optimal.java
 ┃ ┣ NFU.java
 ┗ Main.java
```

---

## Algoritmos Implementados

### 1. FIFO (First In, First Out)
Substitui a página mais antiga carregada na memória.  
📋 **Implementação:** fila que remove a página mais antiga.

### 2. LRU (Least Recently Used)
Remove a página usada há mais tempo.  
📋 **Implementação:** estrutura que rastreia o uso recente.

### 3. NFU (Not Frequently Used)
Baseado na frequência de uso das páginas.  
📋 **Implementação:** Atribui um contador a cada página e incrementa o contador sempre que a página é referenciada. A página com o menor contador é substituída.

### 4. Ótimo (Optimal)
Substitui a página que será usada mais tarde no futuro.  
📋 **Implementação:** previsão futura das referências de página.

---

## 🧪 Exemplo de Saída

```
Páginas lidas: [1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5]
Quartos informados: 3
faltas FIFO: 9
faltas LRU: 10
faltas NFU: 10
faltas Optimal: 7
```

---

## 🖥️ Interface Gráfica 
A interface foi implementada utilizando **Swing**, permitindo:
- Inserir a sequência de páginas (ex:1 2 3);
- Selecionar o número de quadros;
- Visualizar os resultados.

---

## 🚀 Como Executar

### ✅ Pré-requisitos
- Java 21.0.8  

### 🔧 Execução via terminal
```bash
java -cp out/production/PJ-SO-ATV Main
```

---

## Resultados Esperados
O simulador permite avaliar a eficiência relativa dos algoritmos de substituição de páginas, identificando:
- O número de faltas de páginas por método;
- O comportamento de cada algoritmo sob diferentes padrões de acesso;
- Comparativos de desempenho visualizados em gráficos.

---

## Autores
- Alberto P Lopes

---

## Rositorio
- https://github.com/Betalha/Simulador-de-algoritmos-de-substituicao-de-paginas


