package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Dictionary;
import com.example.app.domain.Word;
import com.example.app.mapper.WordMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordMapper wordMapper;
    private final DictService dictService;
    private final DictWordService dictWordService;
    private static final int UNSET_CATEGORY_ID = 99;
    
    @Override
    public List<Word> getAll(Integer userId) {
        // 単語のリストを取得
        List<Word> wordList = wordMapper.selectAll(userId);

        // 各単語に対応する辞書情報を付加
        for (Word word : wordList) {
            // 単語に登録された辞書リストを取得
            word.setRegisteredDictList(dictWordService.getDictListByWordId(word.getId()));

            // 辞書が登録されていない場合、デフォルトの辞書を設定する
            List<Dictionary> dictList = word.getRegisteredDictList();
            if (dictList.isEmpty()) {
                dictWordService.setDictWord(word.getId(), UNSET_CATEGORY_ID); // 99は未設定カテゴリー
                word.setRegisteredDictList(dictService.setDefaultDict(dictList, userId, 99));
            }
        }

        System.out.println("一覧表示用の単語リスト->" + wordList);
        return wordList;
    }

    @Override
    public Word getWordById(Long wordId) {
        // wordIdに対応した単語情報を取得
        Word word = wordMapper.selectWordById(wordId);
        return word;
    }

    @Override
    public void setPageViewsForWordById(Long wordId) {
        // wordIdに対応した単語情報の閲覧回数をインクリメント
        wordMapper.incrementPageViews(wordId);
    }

    @Override
    public void setLastViewedDateForWordById(Long wordId) {
        // wordIdに対応した単語の最終閲覧日を更新
        wordMapper.updateWordLastViewedById(wordId);
    }

    @Override
    public void setEditedWord(Word word) {
        // 単語情報を更新
        wordMapper.updateWordByEditedWord(word);
    }

    @Override
    public void deleteWordById(Long wordId) {
        // wordIdに対応する単語を削除
        wordMapper.deleteWordById(wordId);
    }

    @Override
    public void setNewWord(Integer userId, Word word) {
        // 新しい単語を登録
        wordMapper.insertWord(userId, word);
    }

    @Override
    public Long getLastInsertedId() {
        // 最後に挿入された単語のIDを取得
        return wordMapper.selectLastInsertedId();
    }

    @Override
    public int getTotalWords(Integer userId) {
        // ユーザーごとの全単語数を取得
        return wordMapper.countTotalWords(userId);
    }
}
