var gulp = require("gulp");
var $ = require("gulp-load-plugins")();

/* init */
gulp.task("lib", function() {
    var bower = require('main-bower-files');
    /* javascript */
    console.log('bower(): ' + bower());
    gulp.src(bower())
        .pipe($.filter([
            "**/*.js",
            "!**/require.js"
        ]))
        .pipe($.concat("lib.js"))
        .pipe(gulp.dest("./dist/js"));

    gulp.src("./bower_components/bootstrap/fonts/**")
        .pipe(gulp.dest("./dist/fonts"));
    gulp.src("./bower_components/font-awesome/fonts/**")
        .pipe(gulp.dest("./dist/fonts"));
    gulp.src("./bower_components/fuelux/fonts/**")
        .pipe(gulp.dest("./dist/fonts"));


});

/* localserver */
var browser = require("browser-sync");
gulp.task("server", function() {
    browser({
        server: {
            baseDir: "./dist"
        }
    });
});

/* html */
gulp.task("htmlhint", function() {
    gulp.src("./src/**/*.html")
        .pipe($.plumber())
        .pipe($.htmlhint())
        .pipe($.htmlhint.reporter())
        .pipe(browser.reload({stream:true}));
});

// freemarker
gulp.task("ftl", function() {
    gulp.src([
        "./mock/room/message.json",
        "./mock/user/userDetail.json",
        "./mock/user/follow.json",
        "./mock/items.json",
        "./mock/item/itemDetail.json"
    ])
        .pipe($.plumber())
        .pipe($.freemarker({
            viewRoot: __dirname + "/../views/freemarker",
            options: {}
        }))
        .pipe(gulp.dest("./dist"))
        .pipe(browser.reload({stream:true}));
});

/* stylesheet */
gulp.task("sass", function() {
    gulp.src("./sass/**/*.scss")
        .pipe($.plumber())
        .pipe($.sass())
        .pipe($.autoprefixer())
        .pipe(gulp.dest("./dist/css"))
        .pipe(browser.reload({stream:true}));
});

gulp.task("less", function() {
    gulp.src([
        "./less/style.less"
    ])
        .pipe($.plumber())
        .pipe($.less())
        .pipe($.autoprefixer())
        .pipe(gulp.dest("./dist/css"))
        .pipe(browser.reload({stream:true}));
});

/* javascript */
gulp.task("js", function() {
    gulp.src(["./js/**/*.js"])
        .pipe($.plumber())
        .pipe($.jshint())
        .pipe($.jshint.reporter())
//        .pipe($.uglify())
        .pipe($.concat("main.js"))
        .pipe(gulp.dest("./dist/js"))
        .pipe(browser.reload({stream:true}));
});

gulp.task("build", ["lib", "js", "less"/*, "ftl"*/]);

gulp.task("deploy", function() {
    gulp.src(["./dist/js/**/*.js", "./dist/css/**/*.css", "./dist/fonts/**", "./dist/image/**"],
            { base: "dist" })
        .pipe(gulp.dest("../public/"));
});

/* default */
gulp.task("default", ["server"],  function() {
    gulp.watch(["./js/**/*.js"],["js"]);
    gulp.watch("./less/**/*.less",["less"]);
    gulp.watch(["../../resources/views/freemarker/**/*.ftl", "./mock/**/*.json"],["ftl"]);
});

