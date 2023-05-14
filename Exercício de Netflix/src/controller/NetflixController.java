package controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;

import br.edu.fateczl.filaobj.Fila;
import br.edu.fateczl.lista.listaObj.Lista;
import model.Serie;



public class NetflixController implements INetflixController {
	
	
	Lista[] listaFilmes = new Lista[7];

	
	public NetflixController() {
		super();
	}

	public void lerArquivo(String path, String arquivo) throws IOException {
		
		File arq = new File (path, arquivo);
		
		
		//Se existir e for arquivo...
		
		if(arq.exists() && arq.isFile()) {
			
			
			//Abertura do CSV
			
			FileInputStream abreFluxoArq = new FileInputStream(arq);
			InputStreamReader leitorFluxo = new InputStreamReader(abreFluxoArq);
			BufferedReader buffer = new BufferedReader(leitorFluxo);
			
			
			//Pular cabeçalho
			
			String linha = buffer.readLine();
			
			//1ª linha
			
			linha = buffer.readLine();
			
			String [] vetLinha = linha.split(";");
			
			String generoAuxiliar = vetLinha[0];
			
			Fila [] fila = new Fila[10];
		
			//Gerando fila de Animação
			
			int cont=0;
			
			fila[cont] = new Fila();
			
			fila[cont].insert(linha);
			
			//Gerar CSV de Animacao
			
			String nomeCSV = generoAuxiliar+".csv";
			
			gerarCSV(path, nomeCSV, linha);
			
			linha = buffer.readLine();
			
			
			//A partir da próxima linha
			
			while(linha != null) {
			
				String [] vetorLinha = linha.split(";");
				String colunaGenero = vetorLinha[0];
				
				if(colunaGenero.equals(generoAuxiliar)) {
					fila[cont].insert(linha);
				}
				
				else {
					
					cont = cont + 1;
					fila[cont] = new Fila();
					fila[cont].insert(linha);
					
				}
				
				//Pular para a próxima linha
				
				generoAuxiliar = colunaGenero;
				nomeCSV = generoAuxiliar+".csv";
				gerarCSV(path, nomeCSV, linha);
				linha = buffer.readLine();
				
				
			}
			
			buffer.close();
			leitorFluxo.close();
			abreFluxoArq.close();
			
		}
		
		
		
	 
		
	}

	
	public void gerarCSV(String path, String nomeCSV, String linha) throws IOException {
		
		
		//Verificar diretório
		
		File dir = new File(path);
		
		if(dir.exists() && dir.isDirectory()) {
			
			File generoCSV = new File(path, nomeCSV);
			
			//Se o arquivo ainda não existir, cria
			
			if(!generoCSV.exists()) {
				generoCSV.createNewFile();
				
			} 
			
				
			
			
			//Se existir, prossegue para o preenchimento
			
			FileWriter abrirArquivo = new FileWriter(generoCSV, true); //O parâmetro true vai garantir que o arquivo seja aberto em modo de apêndice e insira os dados sem fazer sobreposição do conteúdo anterior
			PrintWriter escreverArquivo = new PrintWriter(abrirArquivo);
			
			escreverArquivo.println();
			escreverArquivo.println(linha);
			escreverArquivo.flush();
			escreverArquivo.close();
			abrirArquivo.close();
			
				
		
			
			
			}
		}

	public void listaArquivo(String path, String arquivo) throws IOException {
		
		File arq = new File (path, arquivo);
		
		
		//Se existir e for arquivo...
		
		if(arq.exists() && arq.isFile()) {
			
			
			//Abertura do CSV
			
			FileInputStream abreFluxoArq = new FileInputStream(arq);
			InputStreamReader leitorFluxo = new InputStreamReader(abreFluxoArq);
			BufferedReader buffer = new BufferedReader(leitorFluxo);
			
			
			//Pular cabeçalho
			
			String linha = buffer.readLine();
			
			//1ª linha
			
			linha = buffer.readLine();
			
			Lista [] lista = new Lista[10];
			
			int tamanhoListas = lista.length;
			
			//Inicializando listas
			
		
			for(int i=0; i < tamanhoListas; i++) {
				lista[i] = new Lista();
			}
			
			
			String status = "Renewed";
		
			/*1ª lista inicializada
			
			lista[cont] = new Lista();*/
			
			while(linha != null) {
			
				String [] vetLinha = linha.split(";");
				
				//Gerando listas
			
				if(vetLinha[6].equals(status)) {
					
					//condição de comparar o ano de premiação
					
					//Percorrer o vetor de Listas
					
					for(int i=0; i < tamanhoListas; i++) {
						
						try {
							
							if(lista[i].isEmpty()) {
								lista[i].addFirst(linha);
								System.out.println("LISTA DO ANO " +vetLinha[4]+ "gerado");
								break;
							} 
							 else {
								
								//Pegar a lista e pegar o primeiro elemento dela
								//Armazenar em uma String para Comparar
								
								Object Serie = lista[i].get(0);
								String [] anoComparar = Serie.toString().split(";");
								
								if(vetLinha[4].contains(anoComparar[4])) {
							
									//Se o ano de premiação for igual ao anoComparar, adiciona o elemento nessa lista
									
									lista[i].addLast(linha);		
									break;
									
								}
								
							}
							
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} //Fim do laço
					
					
					//Pular para a próxima linha
					
					String anoAuxiliar = "Ano de Premiaçao "+vetLinha[4];
					String nomeCSV = anoAuxiliar+".csv";
					gerarCSV(path, nomeCSV, linha);
				
					
					
				}
			
				linha = buffer.readLine();
			
				
				
				
			}
			
		
	}
		
		 	
		
	} //FIM LISTAR ARQUIVO
	
	

	public void adicionarFilmes(String path, String arquivo) throws Exception {
			
			
		
		//Inicializando listas
		
		for(int i = 0; i < 7; i++) {
			listaFilmes[i] = new Lista();
		}
		
		
		
		File arq = new File (path, arquivo);
		
		
		//Se existir e for arquivo...
		
		if(arq.exists() && arq.isFile()) {
			
			
			//Abertura do CSV
			
			FileInputStream abreFluxoArq = new FileInputStream(arq);
			InputStreamReader leitorFluxo = new InputStreamReader(abreFluxoArq);
			BufferedReader buffer = new BufferedReader(leitorFluxo);
			
			
			//Pular cabeçalho
			
			String linha = buffer.readLine();
			
			//1ª linha
			
			linha = buffer.readLine();
			
			while(linha != null) {
			
			Object Serie = linha;
			String[] s = Serie.toString().split(";");
			String p = s[11];
			int hash = hashCode(p);
			
			if(listaFilmes[hash].isEmpty()) {
				listaFilmes[hash].addFirst(linha);
				System.out.println("Lista de Filmes com " +hash+ "estrelas criado");
			}
			else {
				listaFilmes[hash].addLast(linha);
			}
		
			linha = buffer.readLine();
			
		}
			
		}

		
	}
	
	
	
	public Serie buscarSerie(int stars) throws Exception{
	
		Lista l = listaFilmes[stars];
		int tamanho = l.size();
		
		System.out.println("LISTA DE FILMES DE " +stars+ "ESTRELAS");
		
		for(int i = 0; i < tamanho; i++) {
			Object serie = l.get(i);
			String [] serieInfo = serie.toString().split(";");
			
			
	
				System.out.println("\n" +serieInfo[1]);
			
			
		}
		
		return null;
		
	}
	
	
	private int hashCode(String p) {
		
		int imdb = Integer.parseInt(p);
		int posicao = imdb/14;
		
		return posicao;
		
	}
	
	
	
	}
	
	
	

