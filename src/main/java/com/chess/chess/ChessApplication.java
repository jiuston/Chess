package com.chess.chess;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableEurekaClient
@SpringBootApplication
@CssImport("./styles/styles.css")
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
public class ChessApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}

}
