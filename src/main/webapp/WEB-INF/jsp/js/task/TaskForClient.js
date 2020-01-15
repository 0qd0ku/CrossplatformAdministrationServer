var deleteClient = function (taskId, clientId) {
        var authJsonObject = {
            "taskId": taskId,
            "clientId": clientId,
        };
        $.ajax({
            type: "DELETE",
            url: "/api/admin/cancel-task",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(authJsonObject),
            dataType: 'text',
            async: false,
            success: function (response) {
                document.location.href = '/api/admin/task-clients?taskId=' + taskId;
            },
            error: function (exception) {
                alert("Error:" + exception.responseText);
            }
        })
};

var deleteTask = function (taskId, clientId) {
    var authJsonObject = {
        "taskId": taskId,
        "clientId": clientId,
    };
    $.ajax({
        type: "DELETE",
        url: "/api/admin/cancel-task",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(authJsonObject),
        dataType: 'text',
        async: false,
        success: function (response) {
            document.location.href = '/api/admin/client-tasks?clientId=' + clientId;
        },
        error: function (exception) {
            alert("Error:" + exception.responseText);
        }
    })
};

var addTask = function (taskId, clientId) {
    var authJsonObject = {
        "taskId": taskId,
        "clientId": clientId,
    };
    $.ajax({
        type: "POST",
        url: "/api/admin/assign",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(authJsonObject),
        dataType: 'text',
        async: false,
        success: function (response) {
            document.location.href = '/api/admin/all-clients';
        },
        error: function (exception) {
            alert("Error:" + exception.responseText);
        }
    })
};