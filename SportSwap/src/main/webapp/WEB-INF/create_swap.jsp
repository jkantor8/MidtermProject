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
 	
 	<label for="gender">Gender:</label>
 	<select name="gender" id="gender">
 		<option value="Unisex">Unisex</option>
    	<option value="Female">Female</option>
    	<option value="Male">Male</option>
 	</select><br>
 
  	<label for="age_group_id">Age Group:</label>
		<select name="age_group_id" id="age_group_id">
  			<option value="1">YOUTH</option>
  			<option value="2">INTERMEDIATE</option>
  			<option value="3">ADULT</option>
		</select><br>
		
	<label for="condition">Condition:</label>
		<select name="condition_id" id="condition_id">
  			<option value="1">NEW</option>
  			<option value="2">LIGHTLY USED</option>
  			<option value="3">USED</option>
  			<option value="4">HEAVILY USED</option>
		</select><br>
		
	<!--  use user address OR put in a new address to be saved in the db -->	
	<input type="radio" id="user_address" name="choose_address_option" value="user_address">
	<label for="user_address"> Use my address</label><br>
	<input type="radio" id="new_address" name="choose_address_option" value="new_address">
	<label for="new_address">Enter a new address</label><br>
	<!-- *** -->
		
 	<input type="hidden" id="userid" name="userid" value="${loggedInUser.getId()}" />
 	<input type="hidden" id="useraddress" name="useraddress" value="${loggedInUser.getAddress.getId()}" />
 	
 	<input type="submit" name="submit" value="create swap listing">
</form>

</div>