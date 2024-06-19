/**
 * 
 */
    tinymce.init({
        selector: '#detailedDescription',  // idを指定
        language: "ja", // 言語（日本語）
        height: 300, // 高さ（px）
        width: "100%", // 幅
        menubar: false, // メニューバーを表示するかどうか
        plugins: "textcolor", // 文字色、画像ボタン、リンク用のプラグインを適用
        toolbar: [ // ツールバー
			// 戻る 進む | フォーマット | 太字 斜体 | 左寄せ 中央寄せ 右寄せ 均等割付 | 箇条書き 段落番号 インデントを減らす インデント
			"undo redo | formatselect | fontsize forecolor" 
			+ "| bold italic" 
		    + "| alignleft aligncenter alignright alignjustify"
		    + "| bullist numlist outdent indent"
		],
        statusbar: false, // ステータスバーを表示するかどうか
        content_style: "p { margin: 0; }"
    });

