$(document).ready(function () {

    $('#comment-form').on('submit', function (e) {

        e.preventDefault();

        var comment = $('#comment');

        var body = {
            content: comment.val(),
            postId: $('#post').data('post-id')
        };

        fetch('/blog/post/comment', {
            method: 'POST',
            body: JSON.stringify(body),
            headers: {"Content-Type": "application/json"},
            credentials: 'include'
        }).then(function (resp) {
            if (resp.status !== 200) {
                console.log(resp);
                return;
            }
            return resp.json();
        }).then(function (json) {

            $('#comments').prepend("<div> <h5>" + json.commenterName + " <small>" + json.created + "</small>" +
                "</h5> " +
                "<blockquote>" + json.content + "</blockquote>" +
                "</div>");

            comment.val(''); // clear comment form
        });
    });

});