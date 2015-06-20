module.exports = function (grunt) {

    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        concat: {
            files: {
                // 元ファイルの指定
                src : 'WebContent/js/src/concat/*.js',
                // 出力ファイルの指定
                dest: 'WebContent/js/concat/concat.js'
            },
            css: {
                // 元ファイルの指定
                src : 'WebContent/css/src/*.css',
                // 出力ファイルの指定
                dest: 'WebContent/css/concat/concat.css'
            }
        },

        uglify: {
            dist: {
                files: {
                    // 出力ファイル: 元ファイル
                    'WebContent/js/app.min.js': 'WebContent/js/concat/concat.js',
                    'WebContent/js/index.min.js': 'WebContent/js/src/index.js',
                    'WebContent/js/common.min.js': 'WebContent/js/src/common.js',
                    'WebContent/js/salon.min.js': 'WebContent/js/src/salon.js',
                    'WebContent/js/stylist.min.js': 'WebContent/js/src/stylist.js',
                    'WebContent/js/service.min.js': 'WebContent/js/src/service.js',
                    'WebContent/js/coupon.min.js': 'WebContent/js/src/coupon.js',
                    'WebContent/js/album.min.js': 'WebContent/js/src/album.js',
                    'WebContent/js/map.min.js': 'WebContent/js/src/map.js',
                },
            }
        },

        cssmin: {
            minify: {
                files: {
                    'WebContent/css/style.min.css':'WebContent/css/concat/concat.css'
                }
            }
        },

        watch: {
            index: {
                files: 'WebContent/js/src/concat/*.js',
                tasks: ['concat', 'uglify']
            },
            js: {
                files: 'WebContent/js/src/*.js',
                tasks: ['uglify']
            },
            css: {
                files: ['WebContent/css/src/*.css'],
                tasks: ['concat', 'cssmin']
            }
        }
    });

    // プラグインのロード・デフォルトタスクの登録
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.registerTask('default', ['concat', 'uglify', 'cssmin']);
};