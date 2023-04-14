<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Budget</title>

    <!-- My dear old friends. -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>

    <style>
        body {
            padding: 1em;
        }

        #logo {
            font-size: 2em;
            font-weight: bold;
            margin-bottom: 2em;
        }

        .LabelBudget {
            padding-right: 10em;
            background-color: #eee !important;
            font-weight: bold;
            border-radius: 0;
        }

        #TableBudget {
            margin-top: 1em;
            width: 33em;
        }

        .negative-amount {
            color: red !important;
        }

        .zero-amount {
            color: orange !important;
        }

        .positive-amount {
            color: green !important;
        }
    </style>
</head>
<body>
<div id="logo">YNAB</div>

<div>
    <button class="btn btn-light LabelBudget" disabled>Budget</button>
    <button class="btn btn-primary">Update Budget</button>
    <button class="btn btn-success">Add Transaction</button>

    <div id="TableBudget">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col">Category</th>
                <th scope="col">Assigned</th>
                <th scope="col">Activity</th>
                <th scope="col">Remaining</th>
            </tr>
            </thead>
            <tbody>
            <#list transactionList as transaction>
                <#assign remaining = transaction.category.allocated - transaction.amount>
                <tr>
                    <td>${transaction.category.name}</td>
                    <td>${"$" + (transaction.category.allocated / 100)?string("0.00")}</td>
                    <td style="text-decoration: underline;">${"$" + (transaction.amount / 100)?string("0.00")}</td>
                    <td
                            <#if remaining < 0 > class="negative-amount"
                            <#elseIf remaining == 0 > class="zero-amount"
                            <#else> class="positive-amount"
                            </#if>
                    >${"$" + (remaining / 100)?string("0.00")}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>