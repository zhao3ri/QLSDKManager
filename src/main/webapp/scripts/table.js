function jumpPage(pageNo, totalPages) {
    if (pageNo != '') {
        if (!isNaN(parseInt(pageNo, 10))) {
            if (new Number(pageNo) > new Number(totalPages))
                pageNo = totalPages;

            $("#pageNo").val(pageNo);
            $("#mainForm").submit();
        } else {
            $("#jumpPageNo").val("");
        }
    }
}

function sort(orderBy, defaultOrder) {
    if ($("#orderBy").val() == orderBy) {
        if ($("#order").val() == "") {
            $("#order").val(defaultOrder);
        }
        else if ($("#order").val() == "desc") {
            $("#order").val("asc");
        }
        else if ($("#order").val() == "asc") {
            $("#order").val("desc");
        }
    }
    else {
        $("#orderBy").val(orderBy);
        $("#order").val(defaultOrder);
    }

    $("#mainForm").submit();
}

function setPageSize(pageSize) {
    $("#pageNo").val("1");
    $("#pageSize").val(pageSize);

    setCookie("cookie_pageSize", pageSize);

    $("#mainForm").submit();
}

function search() {
    $("#order").val("");
    $("#orderBy").val("");
    $("#pageNo").val("1");

    $("#mainForm").submit();
}

function rangeChangeOperate(sType) {
    if (sType == "search") {
        search();
    } else if (sType == "searchAndValid") {
        searchAndValid();
    } else if (sType == "submit") {
        $("#mainForm").submit();
    }
}


function searchAndValid() {
    var type = $("#type").val();
    var compareType = $("#compareType").val();
    if (compareType != "" && compareType != type) {
        parent.errorTip("对比维度不一致！");
        return;
    }
    search();
}

function confirmAction(url, msg) {
    if (confirm(msg) == true)
        window.location.assign(url);
}

function getRowAndColumn(table) {
    // $(".tab-user td").click(function () {
    //     var tdSeq = $(this).parent().find("td").index($(this)[0]);
    //     var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]);
    //     alert("第" + (trSeq + 1) + "行，第" + (tdSeq + 1) + "列");
    // });
    if (!document.getElementsByTagName || !document.createTextNode) return;
    var rows = table.getElementsByTagName('tr');
    var cols;
    for (i = 0; i < rows.length; i++) {
        rows[i].onclick = function () {
            alert("行:" + eval(this.rowIndex + 1));
        }
        if (i = 0) {
            colsTH = rows[i].getElementsByTagName('th');
            alert(colsTH.length);
            for (k = 0; k < colsTH.length; k++) {
                colsTH[k].onclick = function () {
                    alert("列:" + eval(this.cellIndex + 1));
                }
            }
        } else {
            cols = rows[i].getElementsByTagName('td');

            for (j = 0; j < cols.length; j++) {
                cols[j].onclick = function () {
                    alert("列:" + eval(this.cellIndex + 1));
                }
            }
        }
    }

}