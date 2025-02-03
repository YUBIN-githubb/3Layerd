package com.example.layerd.repository;

import com.example.layerd.dto.MemoResponseDto;
import com.example.layerd.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    MemoResponseDto saveMemo(Memo memo); // 파라미터로 전달되는 메모 객체는 식별자 값이 없는 상태로 전달되어옴
    List<MemoResponseDto> findAllMemos();
    Optional<Memo> findMemoById(Long id);
    int updateMemo(Long id, String title, String contents);
    int updateTitle(Long id, String title);
    int deleteMemo(Long id);
    Memo findMemoByIdOrElseThrow(Long id);

}
