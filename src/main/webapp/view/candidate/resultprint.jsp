<%@ include file="../navbar.jsp"%>
<body>


	<div class="fluid-container px-1 table-light">
		<table id="example" class="table table-sm  table-bordered" style="width: 100%">
      <thead>
      	<tr>
          <th><th>
          <th align="left">Institute Name : CSM Technology</th>
          <th></th>
          <th></th>
        </tr>
        <tr>
          <th>Name : ${CANDIDATE.candFirstname} ${CANDIDATE.candLastname} </th>
          <th> <th>
          <th>Exam Name : ${CANDIDATE.exam.examName} </th>
          <th></th>
        </tr>
        
        <tr>
          <th>S.N.</th>
          <th>Question Type</th>
          <th>Max. Marks</th>
          <th> Currrect Qty </th>
          <th>Marks</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th align="left">1.</th>
          <th align="left">Objective</th>
          <th>1</th>
          <th> ${OBJQTY} </th>
          <td>${OBJ}</td>
        </tr>
        <tr>
          <th align="left">2.</th>
          <th align="left">Subjective</th>
          <th>3</th>
          <th>${SUBQTY}</th>
          <td>${SUB}</td>
        </tr>
        <tr>
          <td align="left">3.</td>
          <td align="left">Total Marks</td>
          <td align="center">${SUB+OBJ}/ ${TOTAL }</td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td align="left">4.</td>
          <td align="left">Result</td>
          <td align="center">
           <c:if test="${RESULT eq 'PASS'}"> <span class="text-success"> PASS </span> </c:if>
           <c:if test="${RESULT eq 'FAIL'}"> <span class="text-danger"> FAIL</span> </c:if>
            </td>
          <td></td>
          <td></td>
        </tr>
        <!-- Add more rows as needed -->
      </tbody>
    </table>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
	    var table = $('#example').DataTable( {
	        lengthChange: false,
	        buttons: [ 'pdf'],
	        
	        paging: false,
	        info: false
	    } );
	 
	    table.buttons().container()
	        .insertBefore( '#example_filter' );
	} );
	</script>
</body>
</html>