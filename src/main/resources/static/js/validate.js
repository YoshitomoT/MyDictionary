/**
 * 
 */

function validateCheckboxes() {
	const checkboxes = document.querySelectorAll('.dict-checkbox');
	let isChecked = false;

	checkboxes.forEach(checkbox => {
		if (checkbox.checked) {
			isChecked = true;
		}
	});

	if (!isChecked) {
		alert('最低1つのチェックボックスを選択してください。');
		return false;
	}

	return true;
}