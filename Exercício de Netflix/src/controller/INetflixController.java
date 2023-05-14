package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Serie;


public interface INetflixController {

	public void lerArquivo(String path, String arquivo) throws IOException;
	public void gerarCSV(String path, String nomeCSV, String linha) throws IOException;
	public void listaArquivo(String path, String arquivo) throws IOException;
	public Serie buscarSerie(int stars) throws Exception;
	public void adicionarFilmes(String path, String arquivo) throws Exception;
}
