<div class="container">
<form action="create_listing.do" method="POST">
<label for="name">name: </label>
  	<input type="text" id="name" name="name"><br>
  	<label for="description">description: </label>
  	<input type="text" id="description" name="description"><br>
	<label for="imgurl">image url:</label>
 	<input type="text" id="imgurl" name="imgurl"><br>
 	<label for="brand">brand:</label>
 	<input type="text" id="brand" name="brand"><br>
 	<input list="gender">
  		<datalist id="gender">
    		<option value="Unisex">
    		<option value="Female">
    		<option value="Male">
  		</datalist>
  		<label for="cars">Age Group:</label>

		<select name="age_group_id" id="age_group_id">
  		<option value="1">YOUTH</option>
  		<option value="2">INTERMEDIATE</option>
  		<option value="3">ADULT</option>

		</select><br>
 	
 	<input type="hidden" id="active" name="active" value="true" />
 	<input type="hidden" id="userid" name="userid" value="${loggedInUser.getId()}" />
 	
 	<input type="submit" name="submit" value="create swap listing">
</form>

</div>