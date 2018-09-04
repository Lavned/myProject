package io.ionic.ylnewapp.bean.market;

import java.util.List;

/**
 * Created by mogojing on 2018/8/10/0010.
 */

public class CapitalFlowsBean {

    /**
     * success : true
     * data : [{"seq":0,"tickerId":"bitcoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin.png","tickerSymbol":"BTC","tickerNameEn":"Bitcoin","tickerNameCh":"比特币","time":1533805187,"price":"43286.61","marketcap":"10.12亿","change24h":"-2.78%","netFlow":"+100万","netPercent":"-4.43%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.78%","outFlowPercent":"52.22%"},{"seq":0,"tickerId":"ethereum","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/ethereum.png","tickerSymbol":"ETH","tickerNameEn":"Ethereum","tickerNameCh":"以太坊","time":1533805187,"price":"2472.12","marketcap":"10.12亿","change24h":"-2.27%","netFlow":"+100万","netPercent":"3.83%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.92%","outFlowPercent":"48.08%"},{"seq":0,"tickerId":"ripple","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/ripple.png","tickerSymbol":"XRP","tickerNameEn":"XRP","tickerNameCh":"瑞波币","time":1533805187,"price":"2.340","marketcap":"10.12亿","change24h":"-2.54%","netFlow":"+100万","netPercent":"1.71%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.86%","outFlowPercent":"49.14%"},{"seq":0,"tickerId":"bitcoin-cash","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin-cash.png","tickerSymbol":"BCH","tickerNameEn":"Bitcoin Cash","tickerNameCh":"比特现金","time":1533805187,"price":"4046.45","marketcap":"10.12亿","change24h":"-3.88%","netFlow":"+100万","netPercent":"2.30%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.15%","outFlowPercent":"48.85%"},{"seq":0,"tickerId":"eos","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/eos.png","tickerSymbol":"EOS","tickerNameEn":"EOS","tickerNameCh":"柚子","time":1533805187,"price":"38.44","marketcap":"10.12亿","change24h":"-6.75%","netFlow":"+100万","netPercent":"-7.04%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"46.48%","outFlowPercent":"53.52%"},{"seq":0,"tickerId":"stellar","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/stellar.png","tickerSymbol":"XLM","tickerNameEn":"Stellar","tickerNameCh":"恒星币","time":1533805187,"price":"1.394","marketcap":"10.12亿","change24h":"-5.68%","netFlow":"+100万","netPercent":"-10.66%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"44.67%","outFlowPercent":"55.33%"},{"seq":0,"tickerId":"litecoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/litecoin.png","tickerSymbol":"LTC","tickerNameEn":"Litecoin","tickerNameCh":"莱特币","time":1533805187,"price":"424.93","marketcap":"10.12亿","change24h":"-5.84%","netFlow":"+100万","netPercent":"1.04%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.52%","outFlowPercent":"49.48%"},{"seq":0,"tickerId":"cardano","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/cardano.png","tickerSymbol":"ADA","tickerNameEn":"Cardano","tickerNameCh":"艾达币","time":1533805187,"price":"0.8031","marketcap":"10.12亿","change24h":"-0.37%","netFlow":"+100万","netPercent":"0.11%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.05%","outFlowPercent":"49.95%"},{"seq":0,"tickerId":"tether","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/tether.png","tickerSymbol":"USDT","tickerNameEn":"Tether","tickerNameCh":"泰达币","time":1533805092,"price":"6.843","marketcap":"10.12亿","change24h":"0.11%","netFlow":"+100万","netPercent":"-8.88%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"45.56%","outFlowPercent":"54.44%"},{"seq":0,"tickerId":"iota","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/iota.png","tickerSymbol":"MIOTA","tickerNameEn":"IOTA","tickerNameCh":"埃欧塔","time":1533805187,"price":"4.295","marketcap":"10.12亿","change24h":"-7.04%","netFlow":"+100万","netPercent":"-6.37%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"46.81%","outFlowPercent":"53.19%"},{"seq":0,"tickerId":"tron","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/tron.png","tickerSymbol":"TRX","tickerNameEn":"TRON","tickerNameCh":"波场","time":1533805187,"price":"0.1677","marketcap":"10.12亿","change24h":"-3.29%","netFlow":"+100万","netPercent":"5.03%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.51%","outFlowPercent":"47.49%"},{"seq":0,"tickerId":"monero","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/monero.png","tickerSymbol":"XMR","tickerNameEn":"Monero","tickerNameCh":"门罗币","time":1533805187,"price":"672.41","marketcap":"10.12亿","change24h":"-3.53%","netFlow":"+100万","netPercent":"-4.14%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.93%","outFlowPercent":"52.07%"},{"seq":0,"tickerId":"ethereum-classic","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/ethereum-classic.png","tickerSymbol":"ETC","tickerNameEn":"Ethereum Classic","tickerNameCh":"以太经典","time":1533805187,"price":"102.59","marketcap":"10.12亿","change24h":"-7.09%","netFlow":"+100万","netPercent":"-4.06%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.97%","outFlowPercent":"52.03%"},{"seq":0,"tickerId":"dash","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/dash.png","tickerSymbol":"DASH","tickerNameEn":"Dash","tickerNameCh":"达世币","time":1533805188,"price":"1206.29","marketcap":"10.12亿","change24h":"-3.57%","netFlow":"+100万","netPercent":"9.81%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"54.91%","outFlowPercent":"45.09%"},{"seq":0,"tickerId":"neo","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/neo.png","tickerSymbol":"NEO","tickerNameEn":"NEO","tickerNameCh":"小蚁","time":1533805187,"price":"150.74","marketcap":"10.12亿","change24h":"-5.24%","netFlow":"+100万","netPercent":"-7.99%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"46.01%","outFlowPercent":"53.99%"},{"seq":0,"tickerId":"binance-coin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/binance-coin.png","tickerSymbol":"BNB","tickerNameEn":"Binance Coin","tickerNameCh":"币安币","time":1533805187,"price":"85.42","marketcap":"10.12亿","change24h":"0.22%","netFlow":"+100万","netPercent":"12.58%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"56.29%","outFlowPercent":"43.71%"},{"seq":0,"tickerId":"nem","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/nem.png","tickerSymbol":"XEM","tickerNameEn":"NEM","tickerNameCh":"新经币","time":1533805187,"price":"0.8200","marketcap":"10.12亿","change24h":"-4.97%","netFlow":"+100万","netPercent":"3.57%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.78%","outFlowPercent":"48.22%"},{"seq":0,"tickerId":"tezos","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/tezos.png","tickerSymbol":"XTZ","tickerNameEn":"Tezos","tickerNameCh":"Tezos (Pre-Launch)","time":1533805187,"price":"10.83","marketcap":"10.12亿","change24h":"-3.12%","netFlow":"+100万","netPercent":"-20.78%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"39.61%","outFlowPercent":"60.39%"},{"seq":0,"tickerId":"okb","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/okb.png","tickerSymbol":"OKB","tickerNameEn":"OKB","tickerNameCh":"OKB","time":1533805187,"price":"17.81","marketcap":"10.12亿","change24h":"-7.93%","netFlow":"+100万","netPercent":"-5.45%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.27%","outFlowPercent":"52.73%"},{"seq":0,"tickerId":"zcash","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/zcash.png","tickerSymbol":"ZEC","tickerNameEn":"Zcash","tickerNameCh":"大零币","time":1533805186,"price":"1106.40","marketcap":"10.12亿","change24h":"-5.23%","netFlow":"+100万","netPercent":"-8.37%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"45.82%","outFlowPercent":"54.18%"},{"seq":0,"tickerId":"omisego","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/omisego.png","tickerSymbol":"OMG","tickerNameEn":"OmiseGO","tickerNameCh":"嫩模币","time":1533805187,"price":"31.11","marketcap":"10.12亿","change24h":"-6.89%","netFlow":"+100万","netPercent":"-5.98%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.01%","outFlowPercent":"52.99%"},{"seq":0,"tickerId":"qtum","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/qtum.png","tickerSymbol":"QTUM","tickerNameEn":"Qtum","tickerNameCh":"量子链","time":1533805188,"price":"36.08","marketcap":"10.12亿","change24h":"-7.43%","netFlow":"+100万","netPercent":"3.51%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.75%","outFlowPercent":"48.25%"},{"seq":0,"tickerId":"0x","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/0x.png","tickerSymbol":"ZRX","tickerNameEn":"0x","tickerNameCh":"0x协议","time":1533805187,"price":"5.910","marketcap":"10.12亿","change24h":"0.22%","netFlow":"+100万","netPercent":"0.52%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.26%","outFlowPercent":"49.74%"},{"seq":0,"tickerId":"bytecoin-bcn","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bytecoin-bcn.png","tickerSymbol":"BCN","tickerNameEn":"Bytecoin","tickerNameCh":"百特币","time":1533805187,"price":"0.0142","marketcap":"10.12亿","change24h":"-2.84%","netFlow":"+100万","netPercent":"5.46%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.73%","outFlowPercent":"47.27%"},{"seq":0,"tickerId":"bitcoin-gold","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin-gold.png","tickerSymbol":"BTG","tickerNameEn":"Bitcoin Gold","tickerNameCh":"比特黄金","time":1533805187,"price":"148.24","marketcap":"10.12亿","change24h":"-3.99%","netFlow":"+100万","netPercent":"-1.78%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"49.11%","outFlowPercent":"50.89%"},{"seq":0,"tickerId":"lisk","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/lisk.png","tickerSymbol":"LSK","tickerNameEn":"Lisk","tickerNameCh":"应用链","time":1533805187,"price":"23.09","marketcap":"10.12亿","change24h":"-4.39%","netFlow":"+100万","netPercent":"-7.97%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"46.02%","outFlowPercent":"53.98%"},{"seq":0,"tickerId":"decred","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/decred.png","tickerSymbol":"DCR","tickerNameEn":"Decred","tickerNameCh":"Decred","time":1533805187,"price":"301.79","marketcap":"10.12亿","change24h":"1.41%","netFlow":"+100万","netPercent":"6.72%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"53.36%","outFlowPercent":"46.64%"},{"seq":0,"tickerId":"zb-blockchain","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/zb-blockchain.png","tickerSymbol":"ZB","tickerNameEn":"ZB Blockchain","tickerNameCh":"ZB生态","time":1533804639,"price":"3.285","marketcap":"10.12亿","change24h":"-3.44%","netFlow":"+100万","netPercent":"-17.14%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"41.43%","outFlowPercent":"58.57%"},{"seq":0,"tickerId":"bitshares","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitshares.png","tickerSymbol":"BTS","tickerNameEn":"BitShares","tickerNameCh":"比特股","time":1533805188,"price":"0.8854","marketcap":"10.12亿","change24h":"-2.71%","netFlow":"+100万","netPercent":"-13.25%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.38%","outFlowPercent":"56.62%"},{"seq":0,"tickerId":"maker","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/maker.png","tickerSymbol":"MKR","tickerNameEn":"Maker","tickerNameCh":"Maker","time":1533805092,"price":"3437.98","marketcap":"10.12亿","change24h":"2.09%","netFlow":"+100万","netPercent":"-18.02%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"40.99%","outFlowPercent":"59.01%"},{"seq":0,"tickerId":"zilliqa","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/zilliqa.png","tickerSymbol":"ZIL","tickerNameEn":"Zilliqa","tickerNameCh":"Zilliqa","time":1533805188,"price":"0.2818","marketcap":"10.12亿","change24h":"-7.92%","netFlow":"+100万","netPercent":"-10.77%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"44.62%","outFlowPercent":"55.38%"},{"seq":0,"tickerId":"aeternity","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/aeternity.png","tickerSymbol":"AE","tickerNameEn":"Aeternity","tickerNameCh":"Aeternity","time":1533805187,"price":"8.972","marketcap":"10.12亿","change24h":"-9.21%","netFlow":"+100万","netPercent":"-13.52%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.24%","outFlowPercent":"56.76%"},{"seq":0,"tickerId":"icon","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/icon.png","tickerSymbol":"ICX","tickerNameEn":"ICON","tickerNameCh":"ICON","time":1533805186,"price":"5.248","marketcap":"10.12亿","change24h":"-4.32%","netFlow":"+100万","netPercent":"-2.68%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"48.66%","outFlowPercent":"51.34%"},{"seq":0,"tickerId":"digibyte","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/digibyte.png","tickerSymbol":"DGB","tickerNameEn":"DigiByte","tickerNameCh":"极特币","time":1533805187,"price":"0.1862","marketcap":"10.12亿","change24h":"-5.99%","netFlow":"+100万","netPercent":"-1.50%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"49.25%","outFlowPercent":"50.75%"},{"seq":0,"tickerId":"ontology","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/ontology.png","tickerSymbol":"ONT","tickerNameEn":"Ontology","tickerNameCh":"本体","time":1533805187,"price":"12.78","marketcap":"10.12亿","change24h":"-11.29%","netFlow":"+100万","netPercent":"-6.62%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"46.69%","outFlowPercent":"53.31%"},{"seq":0,"tickerId":"dogecoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/dogecoin.png","tickerSymbol":"DOGE","tickerNameEn":"Dogecoin","tickerNameCh":"狗狗币","time":1533805161,"price":"0.0166","marketcap":"10.12亿","change24h":"-4.40%","netFlow":"+100万","netPercent":"-4.93%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.53%","outFlowPercent":"52.47%"},{"seq":0,"tickerId":"steem","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/steem.png","tickerSymbol":"STEEM","tickerNameEn":"Steem","tickerNameCh":"斯蒂姆币","time":1533805187,"price":"7.087","marketcap":"10.12亿","change24h":"-2.37%","netFlow":"+100万","netPercent":"6.91%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"53.45%","outFlowPercent":"46.55%"},{"seq":0,"tickerId":"augur","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/augur.png","tickerSymbol":"REP","tickerNameEn":"Augur","tickerNameCh":"Augur","time":1533805187,"price":"158.78","marketcap":"10.12亿","change24h":"-8.41%","netFlow":"+100万","netPercent":"-9.65%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"45.18%","outFlowPercent":"54.82%"},{"seq":0,"tickerId":"moac","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/moac.png","tickerSymbol":"MOAC","tickerNameEn":"MOAC","tickerNameCh":"墨客","time":1533805092,"price":"27.03","marketcap":"10.12亿","change24h":"-0.38%","netFlow":"+100万","netPercent":"-51.21%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"24.40%","outFlowPercent":"75.60%"},{"seq":0,"tickerId":"basic-attention-token","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/basic-attention-token.png","tickerSymbol":"BAT","tickerNameEn":"Basic Attention Token","tickerNameCh":"注意力币","time":1533805187,"price":"1.573","marketcap":"10.12亿","change24h":"-9.90%","netFlow":"+100万","netPercent":"-33.64%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"33.18%","outFlowPercent":"66.82%"},{"seq":0,"tickerId":"verge","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/verge.png","tickerSymbol":"XVG","tickerNameEn":"Verge","tickerNameCh":"Verge","time":1533805187,"price":"0.1031","marketcap":"10.12亿","change24h":"-4.51%","netFlow":"+100万","netPercent":"-17.45%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"41.27%","outFlowPercent":"58.73%"},{"seq":0,"tickerId":"siacoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/siacoin.png","tickerSymbol":"SC","tickerNameEn":"Siacoin","tickerNameCh":"云储币","time":1533805187,"price":"0.0428","marketcap":"10.12亿","change24h":"-4.23%","netFlow":"+100万","netPercent":"-4.99%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.51%","outFlowPercent":"52.49%"},{"seq":0,"tickerId":"bitcoin-diamond","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin-diamond.png","tickerSymbol":"BCD","tickerNameEn":"Bitcoin Diamond","tickerNameCh":"比特钻石","time":1533805187,"price":"10.60","marketcap":"10.12亿","change24h":"3.08%","netFlow":"+100万","netPercent":"-4.99%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.50%","outFlowPercent":"52.50%"},{"seq":0,"tickerId":"bytom","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bytom.png","tickerSymbol":"BTM","tickerNameEn":"Bytom","tickerNameCh":"比原链","time":1533805186,"price":"1.437","marketcap":"10.12亿","change24h":"-7.35%","netFlow":"+100万","netPercent":"-4.47%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.77%","outFlowPercent":"52.23%"},{"seq":0,"tickerId":"rchain","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/rchain.png","tickerSymbol":"RHOC","tickerNameEn":"RChain","tickerNameCh":"RChain","time":1533805092,"price":"3.528","marketcap":"10.12亿","change24h":"-2.00%","netFlow":"+100万","netPercent":"10.82%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"55.41%","outFlowPercent":"44.59%"},{"seq":0,"tickerId":"golem-network-tokens","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/golem-network-tokens.png","tickerSymbol":"GNT","tickerNameEn":"Golem","tickerNameCh":"Golem","time":1533805188,"price":"1.289","marketcap":"10.12亿","change24h":"-2.21%","netFlow":"+100万","netPercent":"-1.83%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"49.09%","outFlowPercent":"50.91%"},{"seq":0,"tickerId":"kucoin-shares","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/kucoin-shares.png","tickerSymbol":"KCS","tickerNameEn":"KuCoin Shares","tickerNameCh":"KuCoin Shares","time":1533805092,"price":"13.62","marketcap":"10.12亿","change24h":"-3.82%","netFlow":"+100万","netPercent":"4.85%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.43%","outFlowPercent":"47.57%"},{"seq":0,"tickerId":"pundi-x","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/pundi-x.png","tickerSymbol":"NPXS","tickerNameEn":"Pundi X","tickerNameCh":"Pundi X","time":1533805095,"price":"0.0125","marketcap":"10.12亿","change24h":"-10.14%","netFlow":"+100万","netPercent":"-16.06%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"41.97%","outFlowPercent":"58.03%"},{"seq":0,"tickerId":"hshare","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/hshare.png","tickerSymbol":"HSR","tickerNameEn":"Hshare","tickerNameCh":"红烧肉","time":1533805188,"price":"27.33","marketcap":"10.12亿","change24h":"-13.54%","netFlow":"+100万","netPercent":"-12.38%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.81%","outFlowPercent":"56.19%"},{"seq":0,"tickerId":"nano","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/nano.png","tickerSymbol":"NANO","tickerNameEn":"Nano","tickerNameCh":"纳诺","time":1533805187,"price":"8.836","marketcap":"10.12亿","change24h":"-2.28%","netFlow":"+100万","netPercent":"-1.89%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"49.05%","outFlowPercent":"50.95%"},{"seq":0,"tickerId":"waves","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/waves.png","tickerSymbol":"WAVES","tickerNameEn":"Waves","tickerNameCh":"波币","time":1533805187,"price":"11.50","marketcap":"10.12亿","change24h":"-0.79%","netFlow":"+100万","netPercent":"-13.78%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.11%","outFlowPercent":"56.89%"},{"seq":0,"tickerId":"stratis","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/stratis.png","tickerSymbol":"STRAT","tickerNameEn":"Stratis","tickerNameCh":"Stratis","time":1533805187,"price":"11.23","marketcap":"10.12亿","change24h":"-6.20%","netFlow":"+100万","netPercent":"-3.97%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"48.01%","outFlowPercent":"51.99%"},{"seq":0,"tickerId":"populous","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/populous.png","tickerSymbol":"PPT","tickerNameEn":"Populous","tickerNameCh":"Populous","time":1533805187,"price":"29.98","marketcap":"10.12亿","change24h":"1.92%","netFlow":"+100万","netPercent":"-0.42%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"49.79%","outFlowPercent":"50.21%"},{"seq":0,"tickerId":"status","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/status.png","tickerSymbol":"SNT","tickerNameEn":"Status","tickerNameCh":"Status","time":1533805187,"price":"0.3120","marketcap":"10.12亿","change24h":"-5.85%","netFlow":"+100万","netPercent":"-8.26%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"45.87%","outFlowPercent":"54.13%"},{"seq":0,"tickerId":"aurora","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/aurora.png","tickerSymbol":"AOA","tickerNameEn":"Aurora","tickerNameCh":"Aurora","time":1533805092,"price":"0.2888","marketcap":"10.12亿","change24h":"-3.72%","netFlow":"+100万","netPercent":"-7.84%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"46.08%","outFlowPercent":"53.92%"},{"seq":0,"tickerId":"maidsafecoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/maidsafecoin.png","tickerSymbol":"MAID","tickerNameEn":"MaidSafeCoin","tickerNameCh":"互联网币","time":1533805092,"price":"2.177","marketcap":"10.12亿","change24h":"0.75%","netFlow":"+100万","netPercent":"-2.84%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"48.58%","outFlowPercent":"51.42%"},{"seq":0,"tickerId":"iostoken","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/iostoken.png","tickerSymbol":"IOST","tickerNameEn":"IOST","tickerNameCh":"IOStoken","time":1533805186,"price":"0.1139","marketcap":"10.12亿","change24h":"-7.97%","netFlow":"+100万","netPercent":"-8.64%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"45.68%","outFlowPercent":"54.32%"},{"seq":0,"tickerId":"mithril","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/mithril.png","tickerSymbol":"MITH","tickerNameEn":"Mithril","tickerNameCh":"秘银币","time":1533805187,"price":"2.673","marketcap":"10.12亿","change24h":"-3.21%","netFlow":"+100万","netPercent":"4.31%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.15%","outFlowPercent":"47.85%"},{"seq":0,"tickerId":"komodo","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/komodo.png","tickerSymbol":"KMD","tickerNameEn":"Komodo","tickerNameCh":"科莫多币","time":1533805187,"price":"8.552","marketcap":"10.12亿","change24h":"0.39%","netFlow":"+100万","netPercent":"-12.29%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.85%","outFlowPercent":"56.15%"},{"seq":0,"tickerId":"digixdao","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/digixdao.png","tickerSymbol":"DGD","tickerNameEn":"DigixDAO","tickerNameCh":"黄金代币","time":1533805187,"price":"460.71","marketcap":"10.12亿","change24h":"-2.14%","netFlow":"+100万","netPercent":"-0.09%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"49.96%","outFlowPercent":"50.04%"},{"seq":0,"tickerId":"aelf","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/aelf.png","tickerSymbol":"ELF","tickerNameEn":"aelf","tickerNameCh":"aelf","time":1533805187,"price":"3.429","marketcap":"10.12亿","change24h":"2.99%","netFlow":"+100万","netPercent":"7.99%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"54.00%","outFlowPercent":"46.00%"},{"seq":0,"tickerId":"huobi-token","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/huobi-token.png","tickerSymbol":"HT","tickerNameEn":"Huobi Token","tickerNameCh":"火币全球通用积分","time":1533805187,"price":"16.27","marketcap":"10.12亿","change24h":"-5.43%","netFlow":"+100万","netPercent":"-0.84%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"49.58%","outFlowPercent":"50.42%"},{"seq":0,"tickerId":"kin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/kin.png","tickerSymbol":"KIN","tickerNameEn":"Kin","tickerNameCh":"Kin","time":1533805092,"price":"0.0010","marketcap":"10.12亿","change24h":"-8.23%","netFlow":"+100万","netPercent":"-36.39%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"31.81%","outFlowPercent":"68.19%"},{"seq":0,"tickerId":"ardor","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/ardor.png","tickerSymbol":"ARDR","tickerNameEn":"Ardor","tickerNameCh":"阿朵币","time":1533805187,"price":"0.7906","marketcap":"10.12亿","change24h":"-2.35%","netFlow":"+100万","netPercent":"4.30%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.15%","outFlowPercent":"47.85%"},{"seq":0,"tickerId":"waltonchain","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/waltonchain.png","tickerSymbol":"WTC","tickerNameEn":"Waltonchain","tickerNameCh":"沃尔顿链","time":1533805186,"price":"19.67","marketcap":"10.12亿","change24h":"-4.68%","netFlow":"+100万","netPercent":"4.20%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.10%","outFlowPercent":"47.90%"},{"seq":0,"tickerId":"wanchain","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/wanchain.png","tickerSymbol":"WAN","tickerNameEn":"Wanchain","tickerNameCh":"Wanchain","time":1533805160,"price":"7.210","marketcap":"10.12亿","change24h":"-5.98%","netFlow":"+100万","netPercent":"-21.29%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"39.36%","outFlowPercent":"60.64%"},{"seq":0,"tickerId":"gxchain","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/gxchain.png","tickerSymbol":"GXS","tickerNameEn":"GXChain","tickerNameCh":"公信宝","time":1533805187,"price":"12.63","marketcap":"10.12亿","change24h":"-11.46%","netFlow":"+100万","netPercent":"-19.01%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"40.49%","outFlowPercent":"59.51%"},{"seq":0,"tickerId":"metaverse","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/metaverse.png","tickerSymbol":"ETP","tickerNameEn":"Metaverse ETP","tickerNameCh":"元界","time":1533805188,"price":"15.49","marketcap":"10.12亿","change24h":"6.96%","netFlow":"+100万","netPercent":"-4.76%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.62%","outFlowPercent":"52.38%"},{"seq":0,"tickerId":"monacoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/monacoin.png","tickerSymbol":"MONA","tickerNameEn":"MonaCoin","tickerNameCh":"萌奈币","time":1533805187,"price":"12.22","marketcap":"10.12亿","change24h":"6.15%","netFlow":"+100万","netPercent":"-13.18%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.41%","outFlowPercent":"56.59%"},{"seq":0,"tickerId":"bumo","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bumo.png","tickerSymbol":"BU","tickerNameEn":"BUMO","tickerNameCh":"BUMO","time":1533805187,"price":"2.083","marketcap":"10.12亿","change24h":"-9.70%","netFlow":"+100万","netPercent":"-9.40%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"45.30%","outFlowPercent":"54.70%"},{"seq":0,"tickerId":"cryptonex","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/cryptonex.png","tickerSymbol":"CNX","tickerNameEn":"Cryptonex","tickerNameCh":"Cryptonex","time":1533729485,"price":"15.44","marketcap":"10.12亿","change24h":"-6.74%","netFlow":"+100万","netPercent":"0.00%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.00%","outFlowPercent":"50.00%"},{"seq":0,"tickerId":"dentacoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/dentacoin.png","tickerSymbol":"DCN","tickerNameEn":"Dentacoin","tickerNameCh":"Dentacoin","time":1533805092,"price":"0.0021","marketcap":"10.12亿","change24h":"-1.70%","netFlow":"+100万","netPercent":"4.65%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.32%","outFlowPercent":"47.68%"},{"seq":0,"tickerId":"aion","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/aion.png","tickerSymbol":"AION","tickerNameEn":"Aion","tickerNameCh":"Aion","time":1533805186,"price":"3.529","marketcap":"10.12亿","change24h":"0.18%","netFlow":"+100万","netPercent":"11.06%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"55.53%","outFlowPercent":"44.47%"},{"seq":0,"tickerId":"zencash","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/zencash.png","tickerSymbol":"ZEN","tickerNameEn":"ZenCash","tickerNameCh":"ZenCash","time":1533805186,"price":"145.59","marketcap":"10.12亿","change24h":"1.34%","netFlow":"+100万","netPercent":"2.21%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.11%","outFlowPercent":"48.89%"},{"seq":0,"tickerId":"funfair","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/funfair.png","tickerSymbol":"FUN","tickerNameEn":"FunFair","tickerNameCh":"FunFair","time":1533805187,"price":"0.1274","marketcap":"10.12亿","change24h":"-3.91%","netFlow":"+100万","netPercent":"-19.37%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"40.31%","outFlowPercent":"59.69%"},{"seq":0,"tickerId":"bancor","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bancor.png","tickerSymbol":"BNT","tickerNameEn":"Bancor","tickerNameCh":"Bancor","time":1533805188,"price":"12.36","marketcap":"10.12亿","change24h":"-2.05%","netFlow":"+100万","netPercent":"-13.78%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.11%","outFlowPercent":"56.89%"},{"seq":0,"tickerId":"chainlink","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/chainlink.png","tickerSymbol":"LINK","tickerNameEn":"ChainLink","tickerNameCh":"ChainLink","time":1533805187,"price":"1.816","marketcap":"10.12亿","change24h":"13.80%","netFlow":"+100万","netPercent":"14.53%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"57.27%","outFlowPercent":"42.73%"},{"seq":0,"tickerId":"holo","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/holo.png","tickerSymbol":"HOT","tickerNameEn":"Holo","tickerNameCh":"Holo","time":1533805187,"price":"0.0047","marketcap":"10.12亿","change24h":"3.35%","netFlow":"+100万","netPercent":"8.84%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"54.42%","outFlowPercent":"45.58%"},{"seq":0,"tickerId":"bitcoin-private","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin-private.png","tickerSymbol":"BTCP","tickerNameEn":"Bitcoin Private","tickerNameCh":"Bitcoin Private","time":1533805092,"price":"29.91","marketcap":"10.12亿","change24h":"-4.04%","netFlow":"+100万","netPercent":"-34.12%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"32.94%","outFlowPercent":"67.06%"},{"seq":0,"tickerId":"monaco","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/monaco.png","tickerSymbol":"MCO","tickerNameEn":"MCO","tickerNameCh":"Monaco","time":1533805187,"price":"37.87","marketcap":"10.12亿","change24h":"-2.22%","netFlow":"+100万","netPercent":"-3.36%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"48.32%","outFlowPercent":"51.68%"},{"seq":0,"tickerId":"decentraland","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/decentraland.png","tickerSymbol":"MANA","tickerNameEn":"Decentraland","tickerNameCh":"Decentraland","time":1533805187,"price":"0.5678","marketcap":"10.12亿","change24h":"-2.48%","netFlow":"+100万","netPercent":"2.63%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.32%","outFlowPercent":"48.68%"},{"seq":0,"tickerId":"odem","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/odem.png","tickerSymbol":"ODE","tickerNameEn":"ODEM","tickerNameCh":"ODEM","time":1533801505,"price":"3.309","marketcap":"10.12亿","change24h":"1.16%","netFlow":"+100万","netPercent":"-60.38%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"19.81%","outFlowPercent":"80.19%"},{"seq":0,"tickerId":"wax","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/wax.png","tickerSymbol":"WAX","tickerNameEn":"WAX","tickerNameCh":"WAX","time":1533805187,"price":"0.6327","marketcap":"10.12亿","change24h":"-4.27%","netFlow":"+100万","netPercent":"5.89%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.95%","outFlowPercent":"47.05%"},{"seq":0,"tickerId":"hycon","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/hycon.png","tickerSymbol":"HYC","tickerNameEn":"HYCON","tickerNameCh":"HYCON","time":1533805187,"price":"0.3065","marketcap":"10.12亿","change24h":"-7.38%","netFlow":"+100万","netPercent":"-3.07%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"48.46%","outFlowPercent":"51.54%"},{"seq":0,"tickerId":"emercoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/emercoin.png","tickerSymbol":"EMC","tickerNameEn":"Emercoin","tickerNameCh":"崛起币","time":1533805161,"price":"13.79","marketcap":"10.12亿","change24h":"-4.32%","netFlow":"+100万","netPercent":"-4.56%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"47.72%","outFlowPercent":"52.28%"},{"seq":0,"tickerId":"davinci-coin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/davinci-coin.png","tickerSymbol":"DAC","tickerNameEn":"Davinci coin","tickerNameCh":"Davinci coin","time":1533804640,"price":"0.0723","marketcap":"10.12亿","change24h":"-4.38%","netFlow":"+100万","netPercent":"-10.51%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"44.75%","outFlowPercent":"55.25%"},{"seq":0,"tickerId":"ark","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/ark.png","tickerSymbol":"ARK","tickerNameEn":"Ark","tickerNameCh":"Ark","time":1533805125,"price":"5.406","marketcap":"10.12亿","change24h":"-4.37%","netFlow":"+100万","netPercent":"-13.53%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"43.24%","outFlowPercent":"56.76%"},{"seq":0,"tickerId":"nebulas-token","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/nebulas-token.png","tickerSymbol":"NAS","tickerNameEn":"Nebulas","tickerNameCh":"Nebulas","time":1533805187,"price":"12.00","marketcap":"10.12亿","change24h":"-9.36%","netFlow":"+100万","netPercent":"-2.17%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"48.92%","outFlowPercent":"51.08%"},{"seq":0,"tickerId":"power-ledger","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/power-ledger.png","tickerSymbol":"POWR","tickerNameEn":"Power Ledger","tickerNameCh":"Power Ledger","time":1533805187,"price":"1.386","marketcap":"10.12亿","change24h":"-1.48%","netFlow":"+100万","netPercent":"0.25%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.12%","outFlowPercent":"49.88%"},{"seq":0,"tickerId":"loopring","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/loopring.png","tickerSymbol":"LRC","tickerNameEn":"Loopring","tickerNameCh":"路印协议","time":1533805186,"price":"0.8885","marketcap":"10.12亿","change24h":"-4.29%","netFlow":"+100万","netPercent":"4.37%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"52.18%","outFlowPercent":"47.82%"},{"seq":0,"tickerId":"kyber-network","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/kyber-network.png","tickerSymbol":"KNC","tickerNameEn":"Kyber Network","tickerNameCh":"Kyber Network","time":1533805188,"price":"3.762","marketcap":"10.12亿","change24h":"-5.42%","netFlow":"+100万","netPercent":"0.70%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.35%","outFlowPercent":"49.65%"},{"seq":0,"tickerId":"reddcoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/reddcoin.png","tickerSymbol":"RDD","tickerNameEn":"ReddCoin","tickerNameCh":"蜗牛币","time":1533805125,"price":"0.0174","marketcap":"10.12亿","change24h":"-7.34%","netFlow":"+100万","netPercent":"3.63%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.81%","outFlowPercent":"48.19%"},{"seq":0,"tickerId":"zcoin","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/zcoin.png","tickerSymbol":"XZC","tickerNameEn":"ZCoin","tickerNameCh":"小零币","time":1533805187,"price":"90.68","marketcap":"10.12亿","change24h":"2.33%","netFlow":"+100万","netPercent":"8.15%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"54.08%","outFlowPercent":"45.92%"},{"seq":0,"tickerId":"theta-token","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/theta-token.png","tickerSymbol":"THETA","tickerNameEn":"Theta Token","tickerNameCh":"Theta Network","time":1533805187,"price":"0.7327","marketcap":"10.12亿","change24h":"-0.24%","netFlow":"+100万","netPercent":"1.23%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"50.61%","outFlowPercent":"49.39%"},{"seq":0,"tickerId":"pivx","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/pivx.png","tickerSymbol":"PIVX","tickerNameEn":"PIVX","tickerNameCh":"普维币","time":1533805092,"price":"8.509","marketcap":"10.12亿","change24h":"-4.56%","netFlow":"+100万","netPercent":"-23.43%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"38.29%","outFlowPercent":"61.71%"},{"seq":0,"tickerId":"nxt","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/nxt.png","tickerSymbol":"NXT","tickerNameEn":"Nxt","tickerNameCh":"未来币","time":1533805188,"price":"0.4801","marketcap":"10.12亿","change24h":"-0.64%","netFlow":"+100万","netPercent":"16.21%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"58.11%","outFlowPercent":"41.89%"},{"seq":0,"tickerId":"tenx","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/tenx.png","tickerSymbol":"PAY","tickerNameEn":"TenX","tickerNameCh":"TenX","time":1533805188,"price":"4.345","marketcap":"10.12亿","change24h":"-0.06%","netFlow":"+100万","netPercent":"10.11%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"55.05%","outFlowPercent":"44.95%"},{"seq":0,"tickerId":"gas","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/gas.png","tickerSymbol":"GAS","tickerNameEn":"Gas","tickerNameCh":"Gas","time":1533805187,"price":"44.79","marketcap":"10.12亿","change24h":"-6.19%","netFlow":"+100万","netPercent":"2.44%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"51.22%","outFlowPercent":"48.78%"},{"seq":0,"tickerId":"elastos","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/elastos.png","tickerSymbol":"ELA","tickerNameEn":"Elastos","tickerNameCh":"亦来云","time":1533805187,"price":"60.42","marketcap":"10.12亿","change24h":"-6.48%","netFlow":"+100万","netPercent":"-8.19%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"45.91%","outFlowPercent":"54.09%"},{"seq":0,"tickerId":"polymath-network","iconUrl":"https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/polymath-network.png","tickerSymbol":"POLY","tickerNameEn":"Polymath","tickerNameCh":"Polymath","time":1533805187,"price":"1.570","marketcap":"10.12亿","change24h":"-10.02%","netFlow":"+100万","netPercent":"-23.54%","inFlow":"¥1.123亿","outFlow":"-¥1.234亿","inFlowPercent":"38.23%","outFlowPercent":"61.77%"}]
     * message : 处理成功！
     */

    private boolean success;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * seq : 0
         * tickerId : bitcoin
         * iconUrl : https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin.png
         * tickerSymbol : BTC
         * tickerNameEn : Bitcoin
         * tickerNameCh : 比特币
         * time : 1533805187
         * price : 43286.61
         * marketcap : 10.12亿
         * change24h : -2.78%
         * netFlow : +100万
         * netPercent : -4.43%
         * inFlow : ¥1.123亿
         * outFlow : -¥1.234亿
         * inFlowPercent : 47.78%
         * outFlowPercent : 52.22%
         *
         "seq": 1,//编号
         "tickerId": "bitcoin",
         "iconUrl": "https://blockchains.oss-cn-shanghai.aliyuncs.com/static/coinInfo/bitcoin.png",//图标
         "tickerSymbol": "BTC",//币种
         "tickerNameEn": "Bitcoin",//英文名
         "tickerNameCh": "比特币",//中文名
         "time": 1533805187,
         "price": "¥43286.61",//价格
         "marketcap": "10.12亿",//市值
         "change24h": "-2.78%",//涨跌幅



         "inFlow": "¥1.123亿",//流入值
         "outFlow": "-¥1.234亿",//流出值
         "netFlow": "+100万",//净流入值

         "inFlowPercent": "47.78%",//流入百分比
         "outFlowPercent": "52.22%",//流出百分比
         "netPercent": "-4.43%",//净流入百分比
         */

        private int seq;
        private String tickerId;
        private String iconUrl;
        private String tickerSymbol;
        private String tickerNameEn;
        private String tickerNameCh;
        private int time;
        private String price;
        private String marketcap;
        private String change24h;
        private String netFlow;
        private String netPercent;
        private String inFlow;
        private String outFlow;
        private String inFlowPercent;
        private String outFlowPercent;

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getTickerId() {
            return tickerId;
        }

        public void setTickerId(String tickerId) {
            this.tickerId = tickerId;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getTickerSymbol() {
            return tickerSymbol;
        }

        public void setTickerSymbol(String tickerSymbol) {
            this.tickerSymbol = tickerSymbol;
        }

        public String getTickerNameEn() {
            return tickerNameEn;
        }

        public void setTickerNameEn(String tickerNameEn) {
            this.tickerNameEn = tickerNameEn;
        }

        public String getTickerNameCh() {
            return tickerNameCh;
        }

        public void setTickerNameCh(String tickerNameCh) {
            this.tickerNameCh = tickerNameCh;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMarketcap() {
            return marketcap;
        }

        public void setMarketcap(String marketcap) {
            this.marketcap = marketcap;
        }

        public String getChange24h() {
            return change24h;
        }

        public void setChange24h(String change24h) {
            this.change24h = change24h;
        }

        public String getNetFlow() {
            return netFlow;
        }

        public void setNetFlow(String netFlow) {
            this.netFlow = netFlow;
        }

        public String getNetPercent() {
            return netPercent;
        }

        public void setNetPercent(String netPercent) {
            this.netPercent = netPercent;
        }

        public String getInFlow() {
            return inFlow;
        }

        public void setInFlow(String inFlow) {
            this.inFlow = inFlow;
        }

        public String getOutFlow() {
            return outFlow;
        }

        public void setOutFlow(String outFlow) {
            this.outFlow = outFlow;
        }

        public String getInFlowPercent() {
            return inFlowPercent;
        }

        public void setInFlowPercent(String inFlowPercent) {
            this.inFlowPercent = inFlowPercent;
        }

        public String getOutFlowPercent() {
            return outFlowPercent;
        }

        public void setOutFlowPercent(String outFlowPercent) {
            this.outFlowPercent = outFlowPercent;
        }
    }
}
