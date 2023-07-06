package com.study.main;

import java.io.IOException;

import com.study.read.ExportExcelReadData;

public class MainClass {
	public static void main(String[] args) throws IOException {
		ExportExcelReadData read = new ExportExcelReadData();
		read.getRowColumnCounts();
	}
}
