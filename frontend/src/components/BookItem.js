import React from "react";
import { useNavigate } from "react-router-dom";

const BookItem = ({ isbn, author, description, image, title, content, score }) => {
    const navigate = useNavigate();
    //
    const goDetail = () => {
        navigate(`/book/${isbn}`);
    };

    return (
        <div className="BookItem">
            <div
                //onClick={goDetail}
                //className={["emotion_img_wrapper", `emotion_img_wrapper_${emotion}`].join(" ")}
                className="book_img_wrapper"
            >
                {/*<img src={process.env.PUBLIC_URL + `assets/emotion${emotion}.png`} />*/}
                <img src={image} />
            </div>
            <div
                onClick={goDetail}
                className="info_wrapper"
            >
                <div className="diary_date">
                    {title}
                </div>
                <div>
                    {author}
                </div>
                <div className="diary_content_preview">
                    {description} {content}
                </div>
            </div>
            <div className="btn_wrapper">
                {/*<MyButton onClick={goEdit} text={"수정하기"} />*/}
            </div>
        </div>
    )
};

export default React.memo(BookItem);