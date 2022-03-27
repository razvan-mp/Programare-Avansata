<html>
<head>
  <title>${title}</title>
</head>
<body style="font-family:Arial, Helvetica, sans-serif;">
  <h1 style="font-size:x-large;text-align: center;padding: 15px;color:darkorchid;">${title}</h1>

  <ol style="font-size:large;text-align:center;padding:5px;color:blueviolet;">
    <#list catalog as item>
      <li> ${item.getId()}, ${item.getTitle()} <em>(${item.getYear()})</em>, <em>${item.getAuthor()}</em> located at ${item.getLocation()}</li>
    </#list>
  </ol>

</body>
</html>