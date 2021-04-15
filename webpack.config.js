var path = require('path');

module.exports = {
    entry: './src/oblig-2/src/index.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
// for watch skal virke i Intellij ved save må Safe Save slås av i settings i IntelliJ
    watch: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/bundle/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env",    "@babel/preset-react",
                            {'plugins': ['@babel/plugin-proposal-class-properties']
                            }
                        ],
                    }
                }]
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    }
};
