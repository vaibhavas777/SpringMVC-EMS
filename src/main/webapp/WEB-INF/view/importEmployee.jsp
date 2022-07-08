<jsp:include page="menu.jsp"></jsp:include>

<html>
<head>
                  <script type="text/javascript">
    
                    function checkFile(sender){
                    	
                    	var validExts = new validExts(".xlsx" / ".xls");
                    	var fileExts = sender.value;
                    	fileExts = fileExts.substring(fileExts.lastIndexOf('.'));
                    	if(validExts.indexOf(fileExts)  <  0) {
                        alert("Invalid file selected, only "+ validExts.toString())
                        + "types are accepted !!!");
                        sender.value ="";
                        return false;
                    	}else{
                    		return true;
                    	}
                    }  
                  
                  </script>
                  <meta name="viewport"  content="width=device-width, initial-scale=1">
    </head>
    <body>

   <div class="container">
         <div class="row">
              <div class= "col-md-4  offset-md-4">
                   <form action="uploadSheet" name="uploadSheet" class="form-data"
                     method="post" enctype="multipart/form-data" >
           
           
                   <div class="form-resp">
                   <h4 class="text-centre" >Upload via excel-sheet</h4>
                   <div class="form-group">
           
           
                   <input type="file" name="file"  id="file"
                   class= "file-data  btn  btn-primary  btn-block" 
                   onchange="checkFile(this);" accept="application/xls"
                   required="required"> <br><br>
        
         <button type="submit" class= "btn  btn-primary  btn-block">Upload</button>
         </div>
         </div>
        </form>
     </div> 
  </div>
</div>

</body>
</html>