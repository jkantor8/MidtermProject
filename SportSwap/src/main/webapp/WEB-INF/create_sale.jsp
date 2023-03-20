<div class="container">
<form action="create_listing.do" method="POST">
	<label for="price">price: </label>
	<input type="text" id="price" name="price"><br>
		
	<!--  use user address OR put in a new address to be saved in the db -->	
	<input type="radio" id="user_address" name="choose_address_option" value="user_address">
	<label for="user_address"> Use my address</label><br>
	<input type="radio" id="new_address" name="choose_address_option" value="new_address">
	<label for="new_address">Enter a new address</label><br>
	<!-- *** -->
		
 	<input type="hidden" id="userid" name="userid" value="${loggedInUser.getId()}" />
 	
 	<input type="submit" name="submit" value="create sale listing">
</form>

</div>