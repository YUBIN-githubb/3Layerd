package com.example.layerd.repository;

import com.example.layerd.dto.MemoResponseDto;
import com.example.layerd.entity.Memo;

import java.util.List;

public interface MemoRepository {

    Memo saveMemo(Memo memo); // 파라미터로 전달되는 메모 객체는 식별자 값이 없는 상태로 전달되어옴
    List<MemoResponseDto> findAllMemos();
    Memo findMemoById(Long id);
    void deleteMemo(Long id);

}
