$(document).ready(function () {
    let urlParams = new URLSearchParams(window.location.search);
    let id = urlParams.get('id');
    let eventSource = new EventSource("/my-page/notifications?authenticatedId=" + id);

    eventSource.onmessage = function (event) {
        var eventData = event.data;
        if (eventData === "vocUpdated") {
            alert("Voc 업데이트가 있습니다!");
            window.location.reload();
        }
    }

    function sendHeartbeat() {
        $.ajax({
            type: "POST",
            url: "/my-page/heartbeat?id=" + id,
            success: function() {
                console.log("Heartbeat 전송");
                window.location.reload();
            },
            error: function(xhr, status, error) {
                console.error("Heartbeat 전송 중 에러:", error);
            }
        });
    }

    setInterval(sendHeartbeat, 15000);
});
