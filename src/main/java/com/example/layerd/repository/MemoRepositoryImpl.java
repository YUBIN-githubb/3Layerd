package com.example.layerd.repository;

import com.example.layerd.dto.MemoResponseDto;
import com.example.layerd.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoRepositoryImpl implements MemoRepository{

    private final Map<Long, Memo> memoList = new HashMap<>();

    @Override
    public Memo saveMemo(Memo memo) {
        //식별자 자동생성 (1씩 증가)
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        //전달받은 메모객체에 식별자 값 부여
        memo.setId(memoId);
        memoList.put(memoId, memo);
        return memo;
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {
        //리스트 초기화
        List<MemoResponseDto> allMemos = new ArrayList<>();

        //데이터베이스에 있는 값을 순회하면서 MemoResponseDto 객체를 만들고 초기화한 빈배열에 add
        for (Memo memo : memoList.values()) {
            MemoResponseDto responseDto = new MemoResponseDto(memo);
            allMemos.add(responseDto);
        }

        return allMemos;
    }

    @Override
    public Memo findMemoById(Long id) {
        return memoList.get(id);
    }
}
