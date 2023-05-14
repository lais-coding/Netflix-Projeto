package main;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.NetflixController;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		
		NetflixController netflix = new NetflixController();
		
		String path = "C:\\TEMP";
		String arquivo = "netflix_originals_series_2.csv";
		
		netflix.lerArquivo(path, arquivo);
		netflix.listaArquivo(path, arquivo);
		try {
			netflix.adicionarFilmes(path, arquivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int stars = Integer.parseInt(JOptionPane.showInputDialog("Insira o nº de estrelas: "));
		
		try {
			netflix.buscarSerie(stars);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
