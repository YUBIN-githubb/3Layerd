package com.example.layerd.service;

import com.example.layerd.dto.MemoRequestDto;
import com.example.layerd.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {

    MemoResponseDto saveMemo(MemoRequestDto dto);
    List<MemoResponseDto> findAllMemos();
    MemoResponseDto findMemoById(Long id);
    MemoResponseDto updateMemo(Long id, String title, String contents);
    MemoResponseDto updateTitle(Long id, String title, String contents);
    void deleteMemo(Long id);
}
