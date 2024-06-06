package com.example.app.domain;

import java.util.Objects;

import lombok.Data;

@Data
public class Dictionary {

	// 辞典ID
	private Integer id;

	// 辞典名
	private String name;
	
	//登録単語数
	private Long wordAmount;
	

	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Dictionary that = (Dictionary) o;
		return id == that.id;
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
