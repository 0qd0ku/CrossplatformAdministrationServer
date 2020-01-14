var edittask = function () {
	var file = document.getElementById("torrentFile").files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onloadend = function() {
		re = reader.result.substr(reader.result.indexOf(',') + 1);
		var authJsonObject = {
			"id": document.getElementById("id").value,
			"name": document.getElementById("name").value,
			"taskType": document.getElementById("taskType").value,
			"version": document.getElementById("version").value,
			"os": document.getElementById("os").value,
			"osType": document.getElementById("osType").value,
			"pathToRunFile": document.getElementById("pathToRunFile").value,
			"torrentFile": re
		};
		$.ajax({
			type: "POST",
			url: "/api/admin/edit-task",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(authJsonObject),
			dataType: 'text',
			async: false,
			success: function (response) {
				alert("Success: " + response);
				document.location.href = '/api/admin/all-tasks';
			},
			error: function (exception) {
				alert("Error:" + exception.responseText);
			}
		})
	}
};