package com.jking.website.service;

import com.jking.website.entity.User;

public interface user {
    User AddUser (String phone);
      String  GetRandom();
      void  Verification(String number,String code);

}
