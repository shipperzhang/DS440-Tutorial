<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'demosite' );

/** Database username */
define( 'DB_USER', 'root' );

/** Database password */
define( 'DB_PASSWORD', 'root' );

/** Database hostname */
define( 'DB_HOST', 'localhost:8888' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         'jt^0xk;RF2?O,L8RP#[{)SdN+,@A>(5(`UOasmsZkZr!BBQ~S]R#2mo*:%0Y}h3>' );
define( 'SECURE_AUTH_KEY',  '(j1ex/gq]Op5FzVAHYu-*D+0|l;bpL5.=|{P:KAB)i(Ez[YS#`So[;e|X`zBub/(' );
define( 'LOGGED_IN_KEY',    'N3u9).TL$VX=|molinReA<~HEUx`GVN F-4uV5wK[IHz.J`/{wOlXWnmg*hSR5IT' );
define( 'NONCE_KEY',        '9gPveG`g)[zvmM+9FDhQ)U03Fo,GMr:}tWKk6E#N9]Pc*zGvzxg}[NdVXoSU64RL' );
define( 'AUTH_SALT',        '~+!<z[IHhtJit4Rx2[Nnu?0RNft 0T8d.b(WLn#L,LKyA6|{6IcIU&LZI`Ueb^T*' );
define( 'SECURE_AUTH_SALT', 'G@oXE_h`B:)Fe&>$&&YkVFsszy/e+c!9y)#LwWT<cXNy E#>LvKNDY:;G8I4({T$' );
define( 'LOGGED_IN_SALT',   'zXMAjVyL[<,#I[*WUHm@TUuCeL$xz_3?)3[xoo[Df?8g*T<hQ$XcxK5Fe,;yYG]i' );
define( 'NONCE_SALT',       'OmCSA.@_EA.ZNKGn-sFh) 1zF]&~_@^ET3X^J~@d6@848d5cwamm!5keRR<+%ff)' );

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
