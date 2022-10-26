<?php
/*
Template Name: Member Registration
*/

get_header(); 

if ( true === apply_filters( 'graduate_filter_frontpage_content_enable', true ) ) : ?>
	<div class="container page-section">
		<div id="primary" class="content-area">
			<main id="main" class="site-main" role="main">
				<!-- Your content needs to be put here-->
				<form role="form" method="post">
					<div class="form-group">
						<input id="firstname" name="firstname" type="text" placeholder="First Name" 
						class="form-control input-sm"  required="">
					</div>
					<div class="form-group">
						<input id="lastname" name="lastname" type="text" placeholder="Last Name" 
						class="form-control input-sm"  required="">
					</div>
					<div class="form-group">
						<input id="email" name="email" type="text" placeholder="Email Address" 
						class="form-control input-sm"  required="">
					</div>
					<div class="form-group">
						<input id="phone" name="phone" type="text" placeholder="Phone Number" 
						class="form-control input-sm"  required="">
					</div>
					<div class="row justify-content-center">
						<div class="col-xs-4 col-sm-4 col-md-4">
							<input type="submit" class="btn btn-info btn-block" value="Submit" name="submitbtn">
						</div>
					</div>
				</form>
			</main><!-- #main -->
		</div><!-- #primary -->
		<?php
		if ( graduate_is_sidebar_enable() ) {
			get_sidebar();
		} ?>
		</div><!-- .container -->
<?php
endif;
get_footer();
