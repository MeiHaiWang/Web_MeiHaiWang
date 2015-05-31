module.exports = function (grunt) {

    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        concat: {
            files: {
                // 元ファイルの指定
                src : 'WebContent/js/src/*.js',
                // 出力ファイルの指定
                dest: 'WebContent/js/concat/concat.js'
            }
        },

        uglify: {
            dist: {
                files: {
                    // 出力ファイル: 元ファイル
                    'WebContent/js/index.min.js': 'WebContent/js/concat/concat.js'
                }
            }
        },

        watch: {
            js: {
                files: 'WebContent/js/src/*.js',
                tasks: ['concat', 'uglify']
            }
        }
    });

    // プラグインのロード・デフォルトタスクの登録
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.registerTask('default', ['concat', 'uglify']);
};