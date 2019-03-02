/**
 * 版本v0.1.0
 * 1.GET 表示查询 POST 表示 增加  要注意的是所有POST方法都做了拦截处理，只有登录才可以访问
 *
 * 2.如果不提供后端返回的接口,则默认成功后返回的是
 * {
 *      'code': 0,
 *      'message': '成功'
 * }
 */

/**
 1.登录模块
 */

//发送验证码   Post

//登录         Post  
{
    'phonenum':'xxxxxxxxxxx',
    'code':'xxxx'
}
{ //返回结果
    "code": 0,
    "message": "成功",
    "data": {
    "id": 8,
        "phonenum": "xxxxxxxxxxx"
}
}

/**
 * 帖子模块
 * */

//发表帖子   Post
{
    'phonenum':'xxxxxxxxxx',
    'title':'xxx',
    'content':'xxx',
    'file':'xx'//图片
}

// 论坛首页 Get
{
    {  //返回结果
        "code": 0,
        "message": "成功",
        "data": {
        "post": [
            {
                "id": 'xx',
                "title": "xxx",
                "time": "xxx",
                "date": "x天x时x分前发帖",
                "user": {
                    "id": 'x',
                    "phonenum": "xxx"
                }
            },
        ]
            }
            "count": 'xx',  //数据行的数量
            "totalPage": 'xx'  //页码的数量
    }
}
// 查看帖子内容  Get
{    //返回结果
    "code": 0,
    "message": "成功",
    "data": {
    "post": {
        "title": "xxx",
            "content": "xxx",
            "time": "xxx",
            "user": {
            "phonenum": "xxx"  //提问者电话号码
        },
        "pictureUrl": "xxx"
    },
    "reply": {
        "reply": [
            {
                "content": "xxx",
                "time": "xxx",
                "user": {
                    "phonenum": "xxx"  //回答者电话号码
                },
                "pictureUrl": "xxx"
            }
        ],
            "count": 10,
            "totalpage": 2
    }
}
}

//查找帖子（以标题为关键词寻找）Get
{
    //返回结果
    "code": 0,
    "message": "成功",
    "data": {
    "result": [
        {
            "id": xxx,
            "title": "xxx",
            "time": "xxx",
            "user": {
                "id": xxx
            }
        }
    ]
        "count": 'xxx'
        "totalpage": xxx
}
}

/**
 * 回复模块
 */
// 发表回复  Post
{
    file:xxx//(图片),
    ansId:xxx//回答用户的id
    postId:xxx//帖子id
    content:'xxx'
}

// 查看新回复  GET (注意pid是指文章的id，通过websocket协议获得)
{
    //返回结果
    {
        "code": 0,
        "message": "成功",
        "data": {
        "reply": [
            {
                "content": "xxx",
                "time": "xxx",
                "user": {
                    "phonenum": "xxx"
                },
                "pictureUrl": "xxx",
        ]
            "count": xx,
            "totalpage": xx
    }
    }
}