package dao;

import dto.CodeDTO;
import model.Code;
import model.User;

public interface CodeDao {

    void add(Code code);
//todo лучше связать не с юзером а с заказом
    Code getCode(User user);
}
