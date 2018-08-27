const path = require('path')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const CleanWebpackPlugin = require('clean-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')


module.exports = {
  entry: [
    'font-awesome/scss/font-awesome.scss',
    './app/index.js'
  ],
  output: {
    filename: 'bundle.js',
    //  path: path.resolve(__dirname, 'AMS/src/main/webapp/'),
      path: path.resolve(__dirname, 'dist'),
    publicPath: ''
  },
  mode:'development',
  devServer: {
    //  contentBase: path.resolve(__dirname, 'AMS/src/main/webapp/'),
   contentBase: path.resolve(__dirname, 'dist'),
    index: 'index.html',
    port: 9000
  },
  module: {
    rules: [
      {
        test: /\.(xml)$/,
        use: [
          'xml-loader'
        ]
      },
      {
        test: /\.(png|jpg)$/,
        use: [
          'file-loader'
        ]
      },
      {
        test: /\.css$/,
        use:[
          'style-loader', 'css-loader'
        ]
      },
      {
        test: /\.scss$/,
        use:[
           'style-loader', 'css-loader', 'sass-loader'
        ]
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            plugins: ['transform-class-properties']
          }
        }
      },
      {
        test: /font-awesome\.config\.js/,
        use: [
          { loader: 'style-loader' },
          { loader: 'font-awesome-loader' }
        ]
      },
      {
        test: /.(ttf|eot|svg|woff(2)?)(\?[a-z0-9]+)?$/,
        use:[{
          loader: 'file-loader',
          options: {
            name: '[name].[ext]',
            outputPath: 'fonts/'
          }
        }]
      }
    ]
  },
  plugins: [
    new CleanWebpackPlugin(['AMS/src/main/webapp/'], {
        exclude: ['WEB-INF', 'META-INF'],
        verbose: true
    }),
    new HtmlWebpackPlugin({
      title: 'Hello World',
      filename:'index.html'
    })
  ]
}
