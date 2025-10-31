# 🧠 Simulador de Algoritmos de Substituição de Páginas

## 📘 Resumo
Este projeto tem como objetivo **simular e comparar o desempenho** de diferentes **algoritmos de substituição de páginas** utilizados em sistemas de gerenciamento de memória virtual.  
Os algoritmos implementados são: **FIFO**, **LRU**, **Relógio (Clock)**, **Ótimo**, **NFU** e **Envelhecimento (Aging)**.  

O simulador permite observar e comparar o número de faltas de páginas em cada método, auxiliando no entendimento do comportamento dos algoritmos sob diferentes condições de acesso à memória.

---

## 🎯 Objetivos
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
📁 src/
 ┣ 📂 algorithms/
 ┃ ┣ 📜 FIFO.java
 ┃ ┣ 📜 LRU.java
 ┃ ┣ 📜 Clock.java
 ┃ ┣ 📜 Optimal.java
 ┃ ┣ 📜 NFU.java
 ┃ ┗ 📜 Aging.java
 ┣ 📜 Simulator.java
 ┣ 📜 Main.java
 ┗ 📜 Utils.java
```

---

## 🧩 Algoritmos Implementados

### 1. FIFO (First In, First Out)
Substitui a página mais antiga carregada na memória.  
📋 **Implementação:** fila que remove a página mais antiga.

### 2. LRU (Least Recently Used)
Remove a página usada há mais tempo.  
📋 **Implementação:** estrutura que rastreia o uso recente.

### 3. Relógio (Clock)
Fornece uma segunda chance antes da substituição.  
📋 **Implementação:** lista circular de páginas com bit de uso.

### 4. Ótimo (Optimal)
Substitui a página que será usada mais tarde no futuro (referência teórica).  
📋 **Implementação:** previsão futura das referências de página.

*(Extras opcionais: NFU e Envelhecimento).*

---

## 🧪 Exemplo de Saída

```
Sequência de páginas: 1 2 3 4 1 2 5 1 2 3 4 5
Número de quadros: 3

Resultados:
- FIFO: 9 faltas de página
- LRU: 8 faltas de página
- Clock: 8 faltas de página
- Ótimo: 7 faltas de página
```

Se a versão com interface gráfica estiver habilitada, os resultados também serão apresentados em forma de **gráfico comparativo de barras**.

---

## 🖥️ Interface Gráfica (opcional)
A interface foi implementada utilizando **Swing**, permitindo:
- Inserir a sequência de páginas;
- Selecionar o número de quadros;
- Visualizar os resultados e gráficos.

🔗 **Referência visual:** [Simulador online de substituição de páginas](https://sdpm-simulator.netlify.app)

---

## 🚀 Como Executar

### ✅ Pré-requisitos
- Java 11+ instalado  
- IDE (IntelliJ, Eclipse, VS Code) ou terminal

### 🔧 Execução via terminal
```bash
# Compilar
javac -d bin src/**/*.java

# Executar
java -cp bin Main
```

### 🔧 Execução com interface gráfica (se disponível)
```bash
java -cp bin MainGUI
```

---

## 📊 Resultados Esperados
O simulador permite avaliar a eficiência relativa dos algoritmos de substituição de páginas, identificando:
- O número de faltas de páginas por método;
- O comportamento de cada algoritmo sob diferentes padrões de acesso;
- Comparativos de desempenho visualizados em gráficos.

---

## 👨‍💻 Autores
- **Seu Nome Aqui**
- **Nome do colega (se houver)**

---

## 📚 Referências
- TANENBAUM, A. S. *Modern Operating Systems.* 4th ed. Pearson, 2014.  
- SILBERSCHATZ, A.; GALVIN, P. B.; GAGNE, G. *Operating System Concepts.* Wiley, 2020.  
- Site de referência: [https://sdpm-simulator.netlify.app](https://sdpm-simulator.netlify.app)  
- DevMedia. *Introdução à interface GUI no Java.*  
  Disponível em: [https://www.devmedia.com.br/introducao-a-interface-gui-no-java/25646](https://www.devmedia.com.br/introducao-a-interface-gui-no-java/25646)
