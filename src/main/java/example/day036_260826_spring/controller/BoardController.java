package example.day036_260826_spring.controller;

import example.day036_260826_spring.model.dao.BoardDao;

public class BoardController {

//  private BoardController(){}
//  private static final BoardController instance = new BoardController();
//  public static BoardController getInstance(){return instance;}

    private BoardDao bd = BoardDao.getInstance();

}
