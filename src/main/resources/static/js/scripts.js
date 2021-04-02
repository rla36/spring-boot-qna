String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};
$(".answer-write input[type=submit]").click(addAnswer);
function addAnswer(e) {
  e.preventDefault();
  console.log("clicked");
  var queryString = $(".answer-write").serialize();
  var url = $(".answer-write").attr("action");
  $.ajax({
    type: 'post',
    url: url,
    data : queryString,
    dataType : 'json',
    error : onError,
    success : onSuccess
  });
}

function onError(request, error) {
  console.log("error");
  alert("code:"+request.status+"\n"+"error:"+error+"\nMessage: 로그인하십시오."); // 에러원인 찾아내기
}

function onSuccess(data) {
  console.log(data);
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(data.writer.userId, data.formattedWrittenDateTime, data.contents, data.question.id, data.id);
  $(".qna-comment-slipp-articles").prepend(template);
  $("textarea[name=contents]").val("");
}

$(".qna-comment-slipp-articles").on("click", "a.link-delete-article", deleteAnswer);

function deleteAnswer(e) {
  e.preventDefault();

  var deleteButton = $(this);
  var url = deleteButton.attr("href");
  $.ajax({
    type : 'delete',
    url : url,
    dataType : 'json',
    error : function (xhr, status) {
      console.log("error");
    },
    success : function (data, status) {
      console.log("success");
      if (data.valid) {
        deleteButton.closest("article").remove();
      } else {
        alert(data.errorMessage);
      }
    }
  })
}
