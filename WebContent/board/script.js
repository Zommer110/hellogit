function writeSave() {
	if(document.writeform.writer.value == ""){
		alert("작성자를 입력하십시요.");
		document.writeform.writer.focus();
		return false;
	}
}