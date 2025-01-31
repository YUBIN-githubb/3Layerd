package com.example.layerd.repository;

import com.example.layerd.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
}
