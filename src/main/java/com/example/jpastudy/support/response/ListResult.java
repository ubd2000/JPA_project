package com.example.jpastudy.support.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ListResult<T> extends CommonResult {
    private List<T> list;
}
