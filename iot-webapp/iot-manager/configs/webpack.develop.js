const fs = require('fs');
const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const devRoot = path.join(__dirname, '../api');

module.exports = {

    devtool: 'inline-source-map',

    /*入口*/
    entry: {
        app: [
            "babel-polyfill",
            'react-hot-loader/patch',
            path.join(__dirname, '../src/app.js')
        ],
        vendor: ['react', 'react-router-dom', 'redux', 'react-dom', 'react-redux']
    },

    /*输出到dist文件夹，输出文件名字为bundle.js*/
    output: {
        path: path.join(__dirname, '../dist'),
        filename: '[name].[hash].js',
        chunkFilename: '[name].[chunkhash].js'
    },

    /*src文件夹下面的以.js结尾的文件，要使用babel解析*/
    /*cacheDirectory是用来缓存编译结果，下次编译加速*/
    module: {
        rules: [{
            test: /\.js$/,
            use: ['babel-loader?cacheDirectory=true'],
            include: path.join(__dirname, '../src')
        }, {
            test: /\.css$/,
            use: [
                'style-loader',
                'css-loader',
                {
                    loader:'postcss-loader',
                    options:{
                        config:{
                            path:path.resolve(__dirname,'./postcss.config.js')
                        }
                    }
                }
            ]
        }, {
            test: /\.(png|jpg|gif)$/,
            use: [{
                loader: 'url-loader',
                options: {
                    //limit 8192意思是，小于等于8K的图片会被转成base64编码，直接插入HTML中，减少HTTP请求。
                    limit: 8192
                }
            }]
        }]
    },

    devServer: {
        contentBase: path.join(__dirname, '../dist'),
        historyApiFallback: true,
        proxy: {
            '/api': {
                target: devRoot,
                onError: function (err, req, res) {
                    var x = devRoot + '/' + req.method + req.url.split('?')[0].replace(/\//g, "_") + '.json';

                    if (!fs.existsSync(devRoot)) {
                        fs.mkdirSync(devRoot);
                    }

                    if (fs.existsSync(x)) {
                        var data = fs.readFileSync(x);
                        res.write(data);
                    } else {
                        fs.writeFileSync(x, '{"url":"' + req.url + '","data":{}}');
                        res.write('no data');
                    }
                    res.end();
                }
            }

        }
    },

    resolve: {
        alias: {
            pages: path.join(__dirname, '../src/pages'),
            component: path.join(__dirname, '../src/components'),
            router: path.join(__dirname, '../src/router'),
            actions: path.join(__dirname, '../src/redux/actions'),
            reducers: path.join(__dirname, '../src/redux/reducers'),

            images: path.join(__dirname, '../public/images')
        }
    },

    /*plugins*/
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: path.join(__dirname, '../src/index.html')
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor'
        })
    ]

};