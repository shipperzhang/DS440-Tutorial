<?php
/*
Template Name: View Member Table
*/

/*
Retrive data from database
*/
global $wpdb;

$results = $wpdb->get_results('select * from members');

get_header();

if ( true === apply_filters( 'graduate_filter_frontpage_content_enable', true ) ) : ?>
	<div class="container page-section">
		<div id="primary" class="content-area">
			<main id="main" class="site-main" role="main">
				<!-- Your content needs to be put here-->
				<table class="table table-striped">
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email Address</th>
							<th>Phone Number</th>
						</tr>
					</thead>
					<tbody>
						<?php foreach ($results as $member) {?>
							<tr>
								<td><?php echo $member->firstname; ?></td>
								<td><?php echo $member->lastname; ?></td>
								<td><?php echo $member->email; ?></td>
								<td><?php echo $member->phone; ?></td>
							</tr>
						<?php }?>
					</tbody>
				</table>
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
