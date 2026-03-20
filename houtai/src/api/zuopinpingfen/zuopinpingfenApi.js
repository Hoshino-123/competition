import request from '@/utils/request';
// list
export const zuopinpingfenList = (params) => {
    return request({
        url: '/zuopinpingfen/page',
        method: 'post',
        data: params,
    })
};
export const zuopinpingfenDetail = (id) => {
    return request({
        url: '/zuopinpingfen/'+id,
        method: 'get',
    })
};
// add
export const zuopinpingfenSave = (params) => {
    return request({
        url: '/zuopinpingfen',
        method: 'post',
        data: params,
    })
};
// updt
export const zuopinpingfenEdit = (params) => {
    return request({
        url: '/zuopinpingfen',
        method: 'put',
        data: params,
    })
};
// delete
export const zuopinpingfenDelete = (id) => {
    return request({
        url: '/zuopinpingfen/'+id,
        method: 'delete',
    })
};
// pldel
export const zuopinpingfenDeleteList = (zuopinpingfens) => {
    return request({
        url: '/zuopinpingfen/deleteList',
        data:{list:zuopinpingfens},
        method: 'post',
    })
};
// all
export const zuopinpingfenAllList = () => {
    return request({
        url: '/zuopinpingfen',
        method: 'get',
    })
};

export const zuopinpingfen_tj_jingsaileixing = () => {return request({url: '/zuopinpingfen/zuopinpingfen_tj_jingsaileixing',method: 'get',})};export const zuopinpingfen_tj_xuexiaoxinxi = () => {return request({url: '/zuopinpingfen/zuopinpingfen_tj_xuexiaoxinxi',method: 'get',})};export const zuopinpingfen_tj_zuopinmingcheng = () => {return request({url: '/zuopinpingfen/zuopinpingfen_tj_zuopinmingcheng',method: 'get',})};