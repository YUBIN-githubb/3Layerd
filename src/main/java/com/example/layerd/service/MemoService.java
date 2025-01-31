package com.example.layerd.service;

import com.example.layerd.dto.MemoRequestDto;
import com.example.layerd.dto.MemoResponseDto;

public interface MemoService {

    MemoResponseDto saveMemo(MemoRequestDto dto);
}
